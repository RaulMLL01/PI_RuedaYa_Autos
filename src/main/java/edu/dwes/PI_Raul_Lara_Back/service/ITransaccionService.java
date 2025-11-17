package edu.dwes.PI_Raul_Lara_Back.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;

@Service
public interface ITransaccionService {
    List<Transaccion> findAll();

    Optional<Transaccion> findById(Long idv, Long ida, LocalDate fecha);

    Transaccion save(Transaccion t);

    void deleteById(Long idv, Long ida, LocalDate fecha);
}
