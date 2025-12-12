package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Imagen;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Vehiculo;
import edu.dwes.PI_Raul_Lara_Back.repository.IAnuncioRepository;
import edu.dwes.PI_Raul_Lara_Back.repository.IImagenRepository;
import edu.dwes.PI_Raul_Lara_Back.repository.ITransaccionRepository;
import edu.dwes.PI_Raul_Lara_Back.repository.IUsuarioRepository;
import edu.dwes.PI_Raul_Lara_Back.repository.IVehiculoRepository;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.IAnuncioService;

@Service
@Transactional
public class AnuncioServiceImpl implements IAnuncioService {

    @Autowired
    private IAnuncioRepository anuncioRepo;

    @Autowired
    private IVehiculoRepository vehiculoRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private ITransaccionRepository transRepo;

    @Autowired
    private IImagenRepository imagenRepo;

    @Autowired
    private DTOConverter converter;

    // ============================================================
    // BÁSICOS
    // ============================================================

    @Override
    public List<Anuncio> findAll() {
        return anuncioRepo.findAll();
    }

    @Override
    public Optional<Anuncio> findById(Long id) {
        return anuncioRepo.findById(id);
    }

    @Override
    public Anuncio save(Anuncio anuncio) {
        return anuncioRepo.save(anuncio);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NonExistentException {

        Anuncio anuncio = anuncioRepo.findById(id)
                .orElseThrow(() -> new NonExistentException("Anuncio no encontrado"));

        transRepo.deleteByAnuncioId(anuncio.getId());
        imagenRepo.deleteByAnuncio(anuncio);
        anuncioRepo.delete(anuncio);
    }

    // ============================================================
    // DTO
    // ============================================================

    @Override
    public List<AnuncioDTO> findAllDTO() {
        return anuncioRepo.findAll()
                .stream()
                .map(converter::toDTO)
                .toList();
    }

    @Override
    public AnuncioDTO findDTOById(Long id) throws NonExistentException {
        Anuncio a = anuncioRepo.findById(id)
                .orElseThrow(() -> new NonExistentException("Anuncio no encontrado"));

        return converter.toDTO(a);
    }

    // ============================================================
    // CREAR ANUNCIO DESDE DTO
    // ============================================================

    @Override
    public AnuncioDTO createFromDTO(AnuncioDTO dto) throws NonExistentException {

        // Verificar que el vehículo exista
        Vehiculo vehiculo = vehiculoRepo.findById(dto.getVehiculoId())
                .orElseThrow(() -> new NonExistentException("Vehículo no encontrado"));

        // Verificar vendedor
        Usuario vendedor = usuarioRepo.findById(dto.getVendedorId())
                .orElseThrow(() -> new NonExistentException("Vendedor no encontrado"));

        // Construimos el anuncio
        Anuncio anuncio = new Anuncio();
        anuncio.setVehiculo(vehiculo);
        anuncio.setVendedor(vendedor);
        anuncio.setEstado(dto.getEstado() != null ? dto.getEstado() : "Activo");
        anuncio.setDescripcion(dto.getDescripcion());
        anuncio.setFechaPublicacion(LocalDate.now());

        anuncio = anuncioRepo.save(anuncio);

        // Guardar imágenes (si vienen)
        if (dto.getImagenes() != null && !dto.getImagenes().isEmpty()) {
            for (String url : dto.getImagenes()) {
                Imagen img = new Imagen();
                img.setAnuncio(anuncio);
                img.setUrl(url);
                img.setEsPrincipal(false); // puedes cambiarlo si quieres
                imagenRepo.save(img);
            }
        }

        return converter.toDTO(anuncio);
    }

    // ============================================================
    // ACTUALIZAR ANUNCIO
    // ============================================================

    @Override
    public AnuncioDTO updateFromDTO(Long id, AnuncioDTO dto) throws NonExistentException {
        Anuncio anuncio = anuncioRepo.findById(id)
                .orElseThrow(() -> new NonExistentException("Anuncio no encontrado"));

        // No cambiamos vehículo ni vendedor desde aquí
        anuncio.setEstado(dto.getEstado());
        anuncio.setDescripcion(dto.getDescripcion());

        // Si quieres permitir edición de fecha:
        if (dto.getFechaPublicacion() != null) {
            anuncio.setFechaPublicacion(LocalDate.parse(dto.getFechaPublicacion()));
        }

        // Actualización de imágenes (opcional)
        if (dto.getImagenes() != null) {
            // Borramos las imágenes actuales
            imagenRepo.deleteByAnuncioId(id);

            // Insertamos las nuevas
            for (String url : dto.getImagenes()) {
                Imagen img = new Imagen();
                img.setAnuncio(anuncio);
                img.setUrl(url);
                imagenRepo.save(img);
            }
        }

        anuncioRepo.save(anuncio);

        return converter.toDTO(anuncio);
    }

    @Override
    public List<AnuncioDTO> findDisponibles() {
        return anuncioRepo.findByVendidoFalse()
                .stream()
                .map(converter::toDTO)
                .toList();
    }

}
