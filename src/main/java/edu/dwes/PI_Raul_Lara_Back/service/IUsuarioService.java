package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.RegistroDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.UsuarioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;

@Service
public interface IUsuarioService {
    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario findByEmail(String mail) throws NonExistentException;

    void deleteById(Long id);

    List<UsuarioDTO> findAllDTO();

    UsuarioDTO findDTOById(Long id) throws NonExistentException;

    UsuarioDTO createFromDTO(RegistroDTO dto);

    UsuarioDTO updateFromDTO(Long id, UsuarioDTO dto) throws NonExistentException;

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<AnuncioDTO> findAnunciosByUsuario(Long usuarioId) throws NonExistentException;

    UsuarioDTO findPerfilByEmail(String email) throws NonExistentException;

}