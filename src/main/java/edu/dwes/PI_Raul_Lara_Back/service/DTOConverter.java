package edu.dwes.PI_Raul_Lara_Back.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;

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
                v.getMatricula(),
                v.getTipo(),
                v.getPrecioEstimado());
    }

    public Vehiculo toEntity(VehiculoDTO dto) {
        if (dto == null)
            return null;

        Vehiculo v = new Vehiculo();

        v.setMarca(dto.getMarca());
        v.setModelo(dto.getModelo());
        v.setMatricula(dto.getMatricula());
        v.setTipo(dto.getTipo());
        v.setPrecioEstimado(dto.getPrecioEstimado());

        if (dto.getFechaFabricacion() != null) {
            v.setFecha_fabricacion(
                    LocalDate.parse(dto.getFechaFabricacion(), DATE_FMT));
        }

        return v;
    }

    // ---------------------------------------------------------
    // ANUNCIO
    // ---------------------------------------------------------
    public AnuncioDTO toDTO(Anuncio a) {

        if (a == null) {
            return null;
        }

        AnuncioDTO dto = new AnuncioDTO();

        dto.setId(a.getId());
        dto.setFechaPublicacion(
                a.getFechaPublicacion() != null ? a.getFechaPublicacion().toString() : null);
        dto.setEstado(a.getEstado());
        dto.setDescripcion(a.getDescripcion());

        // VENDEDOR
        if (a.getVendedor() != null) {
            dto.setVendedorId(a.getVendedor().getId());
        }

        // VEHÍCULO
        if (a.getVehiculo() != null) {
            dto.setVehiculoId(a.getVehiculo().getId());
            dto.setMarca(a.getVehiculo().getMarca());
            dto.setModelo(a.getVehiculo().getModelo());
            dto.setCombustible(a.getVehiculo().getCombustible());
            dto.setPrecio(a.getVehiculo().getPrecioEstimado());
            dto.setKilometros(a.getVehiculo().getKilometraje());
        }

        // IMÁGENES
        if (a.getImagenes() != null) {
            dto.setImagenes(
                    a.getImagenes()
                            .stream()
                            .map(Imagen::getUrl)
                            .toList());
        }

        return dto;
    }

    public Anuncio toEntity(AnuncioDTO dto, Vehiculo vehiculo, Usuario vendedor) {

        if (dto == null) {
            return null;
        }
        Anuncio a = new Anuncio();

        a.setId(dto.getId()); // normalmente null al crear
        a.setVehiculo(vehiculo);
        a.setVendedor(vendedor);

        if (dto.getFechaPublicacion() != null) {
            a.setFechaPublicacion(LocalDate.parse(dto.getFechaPublicacion()));
        }

        a.setEstado(dto.getEstado());
        a.setDescripcion(null); // si no viene en el DTO, lo ponemos manual o se añade después

        return a;
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
                u.getApellidos(),
                u.getEmail(),
                u.getTelefono(),
                fecha,
                u.getRol() != null ? u.getRol().getNombre() : null);
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
        MensajeDTO dto = new MensajeDTO();

        dto.setId(m.getId());
        dto.setContenido(m.getContenido());
        dto.setFechaEnvio(m.getFechaEnvio().toString());

        dto.setEmisorId(m.getEmisor().getId());
        dto.setEmisorEmail(m.getEmisor().getEmail());
        dto.setEmisorNombre(m.getEmisor().getNombre());

        dto.setReceptorId(m.getReceptor().getId());
        dto.setReceptorEmail(m.getReceptor().getEmail());
        dto.setReceptorNombre(m.getReceptor().getNombre());
        dto.setLeido(m.getLeido());

        return dto;
    }

    public Mensaje toEntity(MensajeDTO dto) {
        if (dto == null)
            return null;

        Mensaje m = new Mensaje();
        m.setId(dto.getId());
        m.setContenido(dto.getContenido());
        m.setLeido(dto.isLeido());

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

        TransaccionDTO dto = new TransaccionDTO();

        dto.setId(t.getId());
        dto.setAnuncioId(t.getAnuncio().getId());
        dto.setVendedorId(t.getVendedor().getId());
        dto.setCompradorId(t.getComprador().getId());
        dto.setTipo(t.getTipo());

        if (t.getFechaMovimiento() != null)
            dto.setFechaMovimiento(t.getFechaMovimiento().toString());

        dto.setPrecio(t.getPrecio());

        return dto;
    }

    public Transaccion toEntity(TransaccionDTO dto) {
        if (dto == null)
            return null;

        Transaccion t = new Transaccion();
        t.setId(dto.getId());
        if (dto.getFechaMovimiento() != null)
            t.setFechaMovimiento(LocalDate.parse(dto.getFechaMovimiento()));
        t.setTipo(dto.getTipo());
        t.setPrecio(dto.getPrecio());

        return t;
    }
}
