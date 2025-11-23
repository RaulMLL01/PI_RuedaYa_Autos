package edu.dwes.PI_Raul_Lara_Back.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.VehiculoDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.UsuarioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.ImagenDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Vehiculo;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Imagen;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Mensaje;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Rol;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;
import edu.dwes.PI_Raul_Lara_Back.model.entities.TransaccionId;

@Component
public class DTOConverter {

    private final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;
    private final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // ---------------------------------------------------------
    // VEHICULO
    // ---------------------------------------------------------
    public VehiculoDTO toDTO(Vehiculo v) {
        if (v == null)
            return null;
        String fecha = (v.getFecha_fabricacion() != null)
                ? v.getFecha_fabricacion().format(DATE_FMT)
                : null;

        return new VehiculoDTO(
                v.getId(),
                v.getMarca(),
                v.getModelo(),
                fecha,
                v.getTipo(),
                v.getPrecioEstimado());
    }

    // ---------------------------------------------------------
    // ANUNCIO
    // ---------------------------------------------------------
    public AnuncioDTO toDTO(Anuncio a) {
        if (a == null)
            return null;

        AnuncioDTO dto = new AnuncioDTO();
        dto.setId(a.getId());
        dto.setFechaPublicacion(a.getFechaPublicacion() != null
                ? a.getFechaPublicacion().format(DATE_FMT)
                : null);
        dto.setEstado(a.getEstado());

        if (a.getVehiculo() != null) {
            dto.setVehiculoId(a.getVehiculo().getId());
            dto.setMarca(a.getVehiculo().getMarca());
            dto.setModelo(a.getVehiculo().getModelo());
            dto.setCombustible(a.getVehiculo().getCombustible());
        }

        if (a.getImagenes() != null) {
            dto.setImagenes(
                    a.getImagenes().stream()
                            .map(Imagen::getUrl)
                            .collect(Collectors.toList()));
        }

        return dto;
    }

    // ---------------------------------------------------------
    // USUARIO
    // ---------------------------------------------------------
    public UsuarioDTO toDTO(Usuario u) {
        if (u == null)
            return null;

        String fecha = (u.getFechaRegistro() != null)
                ? u.getFechaRegistro().toString()
                : null;

        return new UsuarioDTO(
                u.getId(),
                u.getUsername(),
                u.getNombre(),
                u.getEmail(),
                u.getTelefono(),
                fecha,
                u.getRol() != null ? u.getRol().getNombre() : null,
                u.getPassword());
    }

    // ---------------------------------------------------------
    // IMAGEN
    // ---------------------------------------------------------
    public ImagenDTO toDTO(Imagen imagen) {
        if (imagen == null)
            return null;

        return new ImagenDTO(
                imagen.getIdImagen(),
                imagen.getUrl(),
                imagen.isEsPrincipal());
    }

    // ---------------------------------------------------------
    // MENSAJE
    // ---------------------------------------------------------
    public MensajeDTO toDTO(Mensaje m) {
        if (m == null)
            return null;

        MensajeDTO dto = new MensajeDTO();
        dto.setEmisorId(m.getEmisor().getId());
        dto.setFechaEnvio(m.getFechaEnvio().toString());
        dto.setReceptorId(m.getReceptor().getId());
        dto.setContenido(m.getContenido());
        dto.setId(m.getId());

        return dto;
    }

    public Mensaje toEntity(MensajeDTO dto) {
        if (dto == null)
            return null;

        Mensaje m = new Mensaje();
        m.setId(dto.getId());
        m.setContenido(dto.getContenido());

        if (dto.getFechaEnvio() != null) {
            m.setFechaEnvio(LocalDateTime.parse(dto.getFechaEnvio(), DATE_TIME_FMT));
        }
        return m;
    }

    // ---------------------------------------------------------
    // TRANSACCION
    // ---------------------------------------------------------
    public TransaccionDTO toDTO(Transaccion t) {
        if (t == null)
            return null;

        return new TransaccionDTO(
                t.getAnuncio() != null ? t.getAnuncio().getId() : null,
                t.getVendedor() != null ? t.getVendedor().getId() : null,
                t.getTipoTransaccion(),
                t.getFechaMovimiento() != null ? t.getFechaMovimiento().toString() : null);
    }

    public Transaccion toEntity(TransaccionDTO dto) {
        if (dto == null)
            return null;

        Transaccion t = new Transaccion();
        LocalDate fecha = dto.getFechaMovimiento() != null
                ? LocalDate.parse(dto.getFechaMovimiento())
                : null;

        if (dto.getAnuncioId() != null && dto.getUsuarioId() != null && fecha != null) {
            t.setId(new TransaccionId(dto.getUsuarioId(), dto.getAnuncioId(), fecha));
        }

        t.setTipoTransaccion(dto.getTipo());
        t.setFechaMovimiento(fecha);

        return t;
    }
}
