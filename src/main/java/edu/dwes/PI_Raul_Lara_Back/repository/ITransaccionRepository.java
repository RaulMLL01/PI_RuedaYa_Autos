package edu.dwes.PI_Raul_Lara_Back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;
import edu.dwes.PI_Raul_Lara_Back.model.entities.TransaccionId;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion, TransaccionId> {

    @Query("SELECT t.vendedor.id FROM Transaccion t WHERE t.anuncio.id = :id")
    Long findVendedorIdByAnuncioId(@Param("id") Long anuncioId);
}
