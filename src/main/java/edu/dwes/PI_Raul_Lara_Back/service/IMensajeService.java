package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Mensaje;

@Service
public interface IMensajeService {

    List<Mensaje> findAll();

    Optional<Mensaje> findById(Long id);

    Mensaje save(Mensaje m);

    void deleteById(Long id);
}
