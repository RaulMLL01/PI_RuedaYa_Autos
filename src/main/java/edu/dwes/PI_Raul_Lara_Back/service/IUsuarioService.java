package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;

@Service
public interface IUsuarioService extends UserDetailsService {
    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    void deleteById(Long id);

    Usuario findByEmail(String email);
}
