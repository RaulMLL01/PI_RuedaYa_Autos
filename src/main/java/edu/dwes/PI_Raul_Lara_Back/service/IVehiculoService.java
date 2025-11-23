package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.VehiculoDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Vehiculo;

@Service
public interface IVehiculoService {
    List<Vehiculo> findAll();

    Optional<Vehiculo> findById(Long id);

    Vehiculo save(Vehiculo v);

    void deleteById(Long id);

    List<VehiculoDTO> findAllDTO();

    VehiculoDTO findDTOById(Long id) throws NonExistentException;

    VehiculoDTO createFromDTO(VehiculoDTO dto);

    VehiculoDTO updateFromDTO(Long id, VehiculoDTO dto) throws NonExistentException;
}