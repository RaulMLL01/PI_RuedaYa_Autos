package edu.dwes.PI_Raul_Lara_Back.service;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.RolDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.UsuarioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.VehiculoDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Mensaje;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Rol;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Vehiculo;

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

    public static RolDTO toDTO(Rol rol) {
        return new RolDTO(rol.getId(), rol.getNombre());
    }

    public static VehiculoDTO toDTO(Vehiculo v) {
        return new VehiculoDTO(v.getId(), v.getMarca(), v.getModelo(), v.getAnio(), v.getTipo());
    }

    public static AnuncioDTO toDTO(Anuncio a) {
        return new AnuncioDTO(
                a.getId(),
                a.getVendedor().getId(),
                a.getVehiculo().getId(),
                a.getFechaPublicacion().toString(),
                a.getEstado());
    }

    public static TransaccionDTO toDTO(Transaccion t) {
        return new TransaccionDTO(t.getId(),
                t.getAnuncio().getId(),
                t.getComprador().getId(),
                t.getTipoTransaccion(),
                t.getFechaMovimiento().toString());
    }

    public static MensajeDTO toDTO(Mensaje m) {
        return new MensajeDTO(m.getId(),
                m.getEmisor().getId(),
                m.getReceptor().getId(),
                m.getContenido(),
                m.getContenido());
    }
}
