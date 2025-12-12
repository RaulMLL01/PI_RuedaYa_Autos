package edu.dwes.PI_Raul_Lara_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {

    @Query("SELECT t.vendedor.id FROM Transaccion t WHERE t.anuncio.id = :id")
    Long findVendedorIdByAnuncioId(@Param("id") Long anuncioId);

    void deleteByAnuncioId(Long anuncioId);
}
