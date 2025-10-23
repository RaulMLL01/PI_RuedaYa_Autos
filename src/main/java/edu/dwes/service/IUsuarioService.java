package edu.dwes.service;

import java.util.List;
import java.util.Optional;

import edu.dwes.model.Usuario;

public interface IUsuarioService {
    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    void deleteById(Long id);

    Usuario findByEmail(String email);
}
