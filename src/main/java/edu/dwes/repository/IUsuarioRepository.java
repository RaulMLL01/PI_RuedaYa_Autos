package edu.dwes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.dwes.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
