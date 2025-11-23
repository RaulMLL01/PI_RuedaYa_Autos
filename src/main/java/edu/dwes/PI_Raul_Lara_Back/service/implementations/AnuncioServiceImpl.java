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
import edu.dwes.PI_Raul_Lara_Back.model.entities.Vehiculo;
import edu.dwes.PI_Raul_Lara_Back.repository.IAnuncioRepository;
import edu.dwes.PI_Raul_Lara_Back.repository.IVehiculoRepository;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.IAnuncioService;

@Service
public class AnuncioServiceImpl implements IAnuncioService {

    @Autowired
    private IAnuncioRepository anuncioRepo;
    @Autowired
    private IVehiculoRepository vehiculoRepo;
    @Autowired
    private DTOConverter converter;

    @Override
    public List<Anuncio> findAll() {
        return anuncioRepo.findAll();
    }

    @Override
    public Optional<Anuncio> findById(Long id) {
        return anuncioRepo.findById(id);
    }

    @Override
    @Transactional
    public Anuncio save(Anuncio anuncio) {
        return anuncioRepo.save(anuncio);
    }

    @Override
    public void deleteById(Long id) {
        anuncioRepo.deleteById(id);
    }

    @Override
    public List<AnuncioDTO> findAllDTO() {
        return findAll().stream().map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public AnuncioDTO findDTOById(Long id) throws NonExistentException {
        Anuncio a = anuncioRepo.findById(id).orElseThrow(() -> new NonExistentException("Anuncio no encontrado"));
        return converter.toDTO(a);
    }

    @Override
    @Transactional
    public AnuncioDTO createFromDTO(AnuncioDTO dto) throws NonExistentException {
        if (dto.getVehiculoId() == null)
            throw new IllegalArgumentException("vehiculoId requerido");
        Vehiculo v = vehiculoRepo.findById(dto.getVehiculoId())
                .orElseThrow(() -> new NonExistentException("Vehículo no encontrado"));
        Anuncio a = new Anuncio();
        a.setFechaPublicacion(
                dto.getFechaPublicacion() != null ? LocalDate.parse(dto.getFechaPublicacion()) : LocalDate.now());
        a.setEstado(dto.getEstado());
        a.setVehiculo(v);
        Anuncio saved = anuncioRepo.save(a);
        return converter.toDTO(saved);
    }

    @Override
    @Transactional
    public AnuncioDTO updateFromDTO(Long id, AnuncioDTO dto) throws NonExistentException {
        Anuncio a = anuncioRepo.findById(id).orElseThrow(() -> new NonExistentException("Anuncio no encontrado"));
        if (dto.getFechaPublicacion() != null)
            a.setFechaPublicacion(LocalDate.parse(dto.getFechaPublicacion()));
        a.setEstado(dto.getEstado());
        anuncioRepo.save(a);
        return converter.toDTO(a);
    }
}