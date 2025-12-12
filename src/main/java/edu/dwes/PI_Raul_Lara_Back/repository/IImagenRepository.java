package edu.dwes.PI_Raul_Lara_Back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Imagen;
import jakarta.transaction.Transactional;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Imagen i WHERE i.anuncio.id = :anuncioId")
    void deleteByAnuncioId(Long anuncioId);

    void deleteByAnuncio(Anuncio anuncio);

    List<Imagen> findByAnuncioId(Long anuncioId);
}
