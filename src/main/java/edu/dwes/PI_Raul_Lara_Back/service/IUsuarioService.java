package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;

public interface IUsuarioService {
    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    void deleteById(Long id);

    Usuario findByEmail(String email);
}
