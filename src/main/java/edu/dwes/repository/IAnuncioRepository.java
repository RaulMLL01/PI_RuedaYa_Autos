package edu.dwes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.dwes.model.Anuncio;

public interface IAnuncioRepository extends JpaRepository<Anuncio, Long> {
}
