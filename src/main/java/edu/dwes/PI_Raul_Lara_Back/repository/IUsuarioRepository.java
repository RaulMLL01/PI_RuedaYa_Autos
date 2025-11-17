package edu.dwes.PI_Raul_Lara_Back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);
}
