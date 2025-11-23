package edu.dwes.PI_Raul_Lara_Back.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "anuncio")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anuncio")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_vehiculo", nullable = false, unique = true)
    private Vehiculo vehiculo;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(length = 20)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToOne(mappedBy = "anuncio", cascade = CascadeType.ALL)
    private Transaccion transaccion;

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL)
    private List<Imagen> imagenes = new ArrayList<>();

    public Anuncio() {
    }

    public Anuncio(Long id, Vehiculo vehiculo, LocalDate fechaPublicacion, String estado,
            String descripcion, Transaccion transaccion) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
        this.descripcion = descripcion;
        this.transaccion = transaccion;
        this.imagenes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    @Override
    public String toString() {
        return "Anuncio [id=" + id + ", vehiculo=" + vehiculo + ", fechaPublicacion="
                + fechaPublicacion + ", estado=" + estado + ", descripcion=" + descripcion + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Anuncio other = (Anuncio) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
