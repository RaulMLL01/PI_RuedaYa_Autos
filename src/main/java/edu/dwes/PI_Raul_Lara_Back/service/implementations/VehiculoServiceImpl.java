package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.VehiculoDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Vehiculo;
import edu.dwes.PI_Raul_Lara_Back.repository.IVehiculoRepository;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.IVehiculoService;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    private final IVehiculoRepository vehiculoRepo;
    private final DTOConverter converter;

    public VehiculoServiceImpl(IVehiculoRepository vehiculoRepo, DTOConverter converter) {
        this.vehiculoRepo = vehiculoRepo;
        this.converter = converter;
    }

    @Override
    public List<Vehiculo> findAll() {
        return vehiculoRepo.findAll();
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return vehiculoRepo.findById(id);
    }

    @Override
    @Transactional
    public Vehiculo save(Vehiculo v) {
        return vehiculoRepo.save(v);
    }

    @Override
    public void deleteById(Long id) {
        vehiculoRepo.deleteById(id);
    }

    @Override
    public List<VehiculoDTO> findAllDTO() {
        return findAll().stream().map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public VehiculoDTO findDTOById(Long id) throws NonExistentException {
        Vehiculo v = vehiculoRepo.findById(id).orElseThrow(() -> new NonExistentException("Vehiculo no encontrado"));
        return converter.toDTO(v);
    }

    @Override
    @Transactional
    public VehiculoDTO createFromDTO(VehiculoDTO dto) {

        Vehiculo v = converter.toEntity(dto);
        Vehiculo saved = vehiculoRepo.save(v);

        return converter.toDTO(saved);
    }

    @Override
    @Transactional
    public VehiculoDTO updateFromDTO(Long id, VehiculoDTO dto) throws NonExistentException {
        Vehiculo v = vehiculoRepo.findById(id).orElseThrow(() -> new NonExistentException("Vehiculo no encontrado"));
        v.setMarca(dto.getMarca());
        v.setModelo(dto.getModelo());
        if (dto.getFechaFabricacion() != null)
            v.setFecha_fabricacion(LocalDate.parse(dto.getFechaFabricacion()));
        v.setTipo(dto.getTipo());
        v.setPrecioEstimado(dto.getPrecioEstimado());
        vehiculoRepo.save(v);
        return converter.toDTO(v);
    }
}