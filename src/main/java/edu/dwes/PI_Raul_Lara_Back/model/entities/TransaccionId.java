package edu.dwes.PI_Raul_Lara_Back.model.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class TransaccionId implements Serializable {

    @Column(name = "id_vendedor")
    private Long vendedorId;

    @Column(name = "id_anuncio")
    private Long anuncioId;

    @DateTimeFormat
    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    public TransaccionId() {
    }

    public TransaccionId(Long vendedorId, Long anuncioId, LocalDate fechaPublicacion) {
        this.vendedorId = vendedorId;
        this.anuncioId = anuncioId;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Long getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(Long anuncioId) {
        this.anuncioId = anuncioId;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

}
