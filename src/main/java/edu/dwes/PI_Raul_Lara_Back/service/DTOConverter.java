package edu.dwes.PI_Raul_Lara_Back.service;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.dto.UsuarioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;

@Service
public class DTOConverter {
    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getFechaRegistro(),
                usuario.getRol() != null ? usuario.getRol().getNombre() : null);
    }
}
