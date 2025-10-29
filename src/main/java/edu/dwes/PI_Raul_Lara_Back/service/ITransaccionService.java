package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;

public interface ITransaccionService {
    List<Transaccion> findAll();

    Optional<Transaccion> findById(Long id);

    Transaccion save(Transaccion t);

    void deleteById(Long id);
}
