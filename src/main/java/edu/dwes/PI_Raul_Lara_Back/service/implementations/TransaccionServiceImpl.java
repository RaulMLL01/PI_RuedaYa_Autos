
package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.*;
import edu.dwes.PI_Raul_Lara_Back.repository.*;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.EmailService;
import edu.dwes.PI_Raul_Lara_Back.service.ITransaccionService;

@Service
@Transactional
public class TransaccionServiceImpl implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private IAnuncioRepository anuncioRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DTOConverter dtoConverter;

    // ===============================================================
    // FIND BY ID
    // ===============================================================
    @Override
    public TransaccionDTO findDTO(Long id) throws NonExistentException {
        Transaccion t = transRepo.findById(id)
                .orElseThrow(() -> new NonExistentException("Transacción no encontrada"));
        return dtoConverter.toDTO(t);
    }

    // ===============================================================
    // FIND ALL
    // ===============================================================
    @Override
    public List<TransaccionDTO> findAllDTO() {
        return transRepo.findAll()
                .stream()
                .map(dtoConverter::toDTO)
                .collect(Collectors.toList());
    }

    // ===============================================================
    // CREATE TRANSACTION
    // ===============================================================
    @Override
    public TransaccionDTO createFromDTO(TransaccionDTO dto) throws NonExistentException {

        // ----------- VALIDAR FECHA -----------
        LocalDate fecha = LocalDate.parse(dto.getFechaMovimiento());

        // ----------- CARGAR ENTIDADES -----------
        Usuario comprador = usuarioRepo.findById(dto.getCompradorId())
                .orElseThrow(() -> new NonExistentException("Comprador no encontrado"));

        Usuario vendedor = usuarioRepo.findById(dto.getVendedorId())
                .orElseThrow(() -> new NonExistentException("Vendedor no encontrado"));

        Anuncio anuncio = anuncioRepo.findById(dto.getAnuncioId())
                .orElseThrow(() -> new NonExistentException("Anuncio no encontrado"));
        anuncio.setVendido(true);
        anuncioRepo.save(anuncio);

        // ----------- VALIDAR QUE COMPRADOR ≠ VENDEDOR -----------
        if (comprador.getId().equals(vendedor.getId())) {
            throw new IllegalArgumentException("El comprador y el vendedor no pueden ser el mismo usuario");
        }

        // ----------- VALIDAR QUE EL ANUNCIO SIGUE ACTIVO -----------
        if (!"Activo".equalsIgnoreCase(anuncio.getEstado())) {
            throw new IllegalStateException("El vehículo ya no está disponible para la compra");
        }

        // ----------- CREAR TRANSACCIÓN -----------
        Transaccion trans = new Transaccion();
        trans.setAnuncio(anuncio);
        trans.setVendedor(vendedor);
        trans.setComprador(comprador);
        trans.setFechaMovimiento(fecha);
        trans.setTipo(dto.getTipo());
        trans.setPrecio(anuncio.getVehiculo().getPrecioEstimado());

        // Guardar
        transRepo.save(trans);

        // ----------- ACTUALIZAR ESTADO DEL ANUNCIO -----------
        anuncio.setEstado("VENDIDO");
        anuncioRepo.save(anuncio);

        // ----------- EMAILS -----------
        try {
            emailService.send(
                    comprador.getEmail(),
                    "Confirmación de compra - RuedaYa",
                    "Hola " + comprador.getNombre() + ",\n\n" +
                            "Has comprado el vehículo:\n" +
                            anuncio.getVehiculo().getMarca() + " " + anuncio.getVehiculo().getModelo() + "\n" +
                            "Vendedor: " + vendedor.getNombre() + "\n" +
                            "Fecha: " + fecha + "\n\n" +
                            "Gracias por confiar en RuedaYa.");

            emailService.send(
                    vendedor.getEmail(),
                    "Tu vehículo ha sido vendido - RuedaYa",
                    "Hola " + vendedor.getNombre() + ",\n\n" +
                            "El usuario " + comprador.getNombre() +
                            " ha comprado tu vehículo:\n" +
                            anuncio.getVehiculo().getMarca() + " " + anuncio.getVehiculo().getModelo() + "\n" +
                            "Fecha: " + fecha + "\n\n" +
                            "Saludos,\nEquipo RuedaYa.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtoConverter.toDTO(trans);
    }

    // ===============================================================
    // DELETE
    // ===============================================================
    @Override
    public void delete(Long id) {
        transRepo.deleteById(id);
    }
}
