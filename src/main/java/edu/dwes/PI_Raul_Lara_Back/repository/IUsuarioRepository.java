package edu.dwes.PI_Raul_Lara_Back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("""
                SELECT t
                FROM Transaccion t
                JOIN FETCH t.anuncio a
                JOIN FETCH a.vehiculo v
                WHERE t.vendedor.email = :email
            """)
    List<Transaccion> findTransaccionesByUsuarioEmail(@Param("email") String email);

}
