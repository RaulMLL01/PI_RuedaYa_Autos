package edu.dwes.PI_Raul_Lara_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.Anuncio;

@Repository
public interface IAnuncioRepository extends JpaRepository<Anuncio, Long> {
}
