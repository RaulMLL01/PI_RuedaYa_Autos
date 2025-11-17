package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.RolDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.UsuarioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.VehiculoDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Imagen;
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
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getFechaRegistro().toString(),
                usuario.getRol() != null ? usuario.getRol().getNombre() : null);
    }

    public static RolDTO toDTO(Rol rol) {
        return new RolDTO(rol.getId(), rol.getNombre());
    }

    public static VehiculoDTO toDTO(Vehiculo v) {
        return new VehiculoDTO(v.getId(), v.getMarca(), v.getModelo(), v.getFecha_fabricacion().toString(),
                v.getTipo(), v.getPrecioEstimado());
    }

    public static AnuncioDTO toDTO(Anuncio a) {
        VehiculoDTO vehiculoDTO = null;
        if (a.getVehiculo() != null) {
            vehiculoDTO = new VehiculoDTO(
                    a.getVehiculo().getId(),
                    a.getVehiculo().getMarca(),
                    a.getVehiculo().getModelo(),
                    a.getVehiculo().getFecha_fabricacion().toString(),
                    a.getVehiculo().getTipo(),
                    a.getVehiculo().getPrecioEstimado());
        }

        AnuncioDTO dto = new AnuncioDTO();
        dto.setId(a.getId());
        dto.setVehiculo(vehiculoDTO);
        dto.setFechaPublicacion(a.getFechaPublicacion().toString());
        dto.setEstado(a.getEstado());

        if (a.getImagenes() != null) {
            dto.setImagenes(a.getImagenes()
                    .stream()
                    .map(Imagen::getUrl)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static TransaccionDTO toDTO(Transaccion t) {
        return new TransaccionDTO(
                t.getAnuncio().getId(),
                t.getVendedor().getId(),
                t.getTipoTransaccion(),
                t.getFechaMovimiento().toString());
    }

    public static MensajeDTO toDTO(Mensaje m) {
        return new MensajeDTO(
                m.getId(),
                m.getEmisor().getId(),
                m.getReceptor().getId(),
                m.getContenido(),
                m.getFechaEnvio().toString());
    }

}
