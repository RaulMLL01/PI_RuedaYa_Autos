package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;

@Service
public interface ITransaccionService {
    List<Transaccion> findAll();

    Optional<Transaccion> findById(Long id);

    Transaccion save(Transaccion t);

    void deleteById(Long id);
}
