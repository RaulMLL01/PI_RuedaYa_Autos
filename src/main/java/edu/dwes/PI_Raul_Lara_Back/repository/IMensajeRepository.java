package edu.dwes.PI_Raul_Lara_Back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Mensaje;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;

@Repository
public interface IMensajeRepository extends JpaRepository<Mensaje, Long> {
    @Query("SELECT m.emisor FROM Mensaje m WHERE m.id = :id")
    Usuario findEmisorByMensajeId(@Param("id") Long id);

    @Query("SELECT m.receptor FROM Mensaje m WHERE m.id = :id")
    Usuario findReceptorByMensajeId(@Param("id") Long id);

    List<Mensaje> findByEmisorAndReceptor(Usuario emisor, Usuario receptor);

    @Query("SELECT m FROM Mensaje m WHERE m.emisor = :usuario OR m.receptor = :usuario ORDER BY m.fechaEnvio DESC")
    List<Mensaje> findAllByUsuario(@Param("usuario") Usuario usuario);

    Long countByReceptorEmailAndLeidoFalse(String email);
}
