package edu.dwes.PI_Raul_Lara_Back.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "anuncios")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anuncio")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_vehiculo", nullable = false, unique = true)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vendedor", nullable = false)
    private Usuario vendedor;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(length = 20)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // PostgreSQL TEXT[] puede mapearse como List<String> con @ElementCollection
    @ElementCollection
    @CollectionTable(name = "anuncio_imagenes", joinColumns = @JoinColumn(name = "id_anuncio"))
    @Column(name = "imagen")
    private List<String> imagenes;

    @OneToOne(mappedBy = "anuncio", cascade = CascadeType.ALL)
    private Transaccion transaccion;

    public Anuncio() {
    }

    public Anuncio(Long id, Vehiculo vehiculo, Usuario vendedor, LocalDate fechaPublicacion, String estado,
            String descripcion, List<String> imagenes, Transaccion transaccion) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.vendedor = vendedor;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.transaccion = transaccion;
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

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
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

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    @Override
    public String toString() {
        return "Anuncio [id=" + id + ", vehiculo=" + vehiculo + ", vendedor=" + vendedor + ", fechaPublicacion="
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
