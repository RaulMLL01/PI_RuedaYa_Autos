package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;

public interface IAnuncioService {
    List<Anuncio> findAll();

    Optional<Anuncio> findById(Long id);

    Anuncio save(Anuncio a);

    void deleteById(Long id);
}