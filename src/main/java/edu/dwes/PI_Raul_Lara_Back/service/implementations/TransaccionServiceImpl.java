
package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.*;
import edu.dwes.PI_Raul_Lara_Back.repository.*;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.ITransaccionService;

@Service
public class TransaccionServiceImpl implements ITransaccionService {

    private final ITransaccionRepository repo;
    private final IUsuarioRepository usuarioRepo;
    private final IAnuncioRepository anuncioRepo;
    private final DTOConverter converter;

    public TransaccionServiceImpl(ITransaccionRepository repo, IUsuarioRepository usuarioRepo,
            IAnuncioRepository anuncioRepo, DTOConverter converter) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
        this.anuncioRepo = anuncioRepo;
        this.converter = converter;
    }

    public List<TransaccionDTO> findAllDTO() {
        return repo.findAll().stream()
                .map(t -> new TransaccionDTO(
                        t.getAnuncio().getId(),
                        t.getVendedor().getId(),
                        t.getTipoTransaccion(),
                        t.getFechaMovimiento().toString()))
                .collect(Collectors.toList());
    }

    public TransaccionDTO findDTO(Long anuncioId, Long usuarioId, String fecha) throws NonExistentException {
        TransaccionId id = new TransaccionId(usuarioId, anuncioId, LocalDate.parse(fecha));
        Transaccion t = repo.findById(id).orElseThrow(() -> new NonExistentException("Transacción no encontrada"));
        return new TransaccionDTO(anuncioId, usuarioId, t.getTipoTransaccion(), fecha);
    }

    @Transactional
    public TransaccionDTO createFromDTO(TransaccionDTO dto) throws NonExistentException {
        Usuario u = usuarioRepo.findById(dto.getUsuarioId())
                .orElseThrow(() -> new NonExistentException("Usuario no encontrado"));
        Anuncio a = anuncioRepo.findById(dto.getAnuncioId())
                .orElseThrow(() -> new NonExistentException("Anuncio no encontrado"));
        LocalDate fecha = LocalDate.parse(dto.getFechaMovimiento());
        TransaccionId id = new TransaccionId(dto.getUsuarioId(), dto.getAnuncioId(), fecha);

        Transaccion t = new Transaccion();
        t.setId(id);
        t.setVendedor(u);
        t.setAnuncio(a);
        t.setFechaMovimiento(fecha);
        t.setTipoTransaccion(dto.getTipo());
        repo.save(t);
        return dto;
    }

    public void delete(Long anuncioId, Long usuarioId, String fecha) {
        repo.deleteById(new TransaccionId(usuarioId, anuncioId, LocalDate.parse(fecha)));
    }
}
