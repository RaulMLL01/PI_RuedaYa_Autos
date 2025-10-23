package edu.dwes.service;

import java.util.List;
import java.util.Optional;

import edu.dwes.model.Vehiculo;

public interface IVehiculoService {
    List<Vehiculo> findAll();

    Optional<Vehiculo> findById(Long id);

    Vehiculo save(Vehiculo vehiculo);

    void deleteById(Long id);
}