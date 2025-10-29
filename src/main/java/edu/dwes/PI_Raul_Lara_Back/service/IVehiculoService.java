package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Vehiculo;

public interface IVehiculoService {
    List<Vehiculo> findAll();

    Optional<Vehiculo> findById(Long id);

    Vehiculo save(Vehiculo vehiculo);

    void deleteById(Long id);
}