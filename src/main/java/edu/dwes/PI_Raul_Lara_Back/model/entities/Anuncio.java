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

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_vehiculo", nullable = false, unique = true)
    private Vehiculo vehiculo;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @Column(length = 50)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_vendedor", nullable = false)
    private Usuario vendedor;

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes = new ArrayList<>();

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaccion> historialTransacciones = new ArrayList<>();

    @Column(nullable = false)
    private boolean vendido = false;

    public Anuncio() {
    }

    public Anuncio(Long id, Vehiculo vehiculo, LocalDate fechaPublicacion,
            String estado, String descripcion) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
        this.descripcion = descripcion;
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

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public List<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }

    public void setHistorialTransacciones(List<Transaccion> historialTransacciones) {
        this.historialTransacciones = historialTransacciones;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    @Override
    public String toString() {
        return "Anuncio [id=" + id + ", vehiculo=" + vehiculo +
                ", fechaPublicacion=" + fechaPublicacion +
                ", estado=" + estado + ", descripcion=" + descripcion + "]";
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Anuncio))
            return false;
        Anuncio other = (Anuncio) obj;
        return id != null && id.equals(other.id);
    }

}