package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;

@Service
public interface IAnuncioService {
    List<Anuncio> findAll();

    Optional<Anuncio> findById(Long id);

    Anuncio save(Anuncio anuncio);

    void deleteById(Long id);

    List<AnuncioDTO> findAllDTO();

    AnuncioDTO findDTOById(Long id) throws NonExistentException;

    AnuncioDTO createFromDTO(AnuncioDTO dto) throws NonExistentException;

    AnuncioDTO updateFromDTO(Long id, AnuncioDTO dto) throws NonExistentException;
}