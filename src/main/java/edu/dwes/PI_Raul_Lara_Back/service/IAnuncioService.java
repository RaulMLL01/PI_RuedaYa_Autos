package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;

@Service
public interface IAnuncioService {
    List<Anuncio> findAll();

    Optional<Anuncio> findById(Long id);

    Anuncio save(Anuncio a);

    void deleteById(Long id);
}