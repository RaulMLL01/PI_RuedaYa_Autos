package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Rol;

@Service
public interface IRolService {
    List<Rol> findAll();

    Optional<Rol> findById(Long id);

    Rol save(Rol r);

    void deleteById(Long id);
}