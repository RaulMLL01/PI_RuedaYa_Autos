package edu.dwes.PI_Raul_Lara_Back.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_anuncio", nullable = false, unique = true)
    private Anuncio anuncio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprador", nullable = false)
    private Usuario comprador;

    @Column(name = "tipo_transaccion", nullable = false, length = 20)
    private String tipoTransaccion;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento;

    @Column(name = "duracion_alquiler")
    private Integer duracionAlquiler;

    @Column(name = "precio_alquiler", precision = 10, scale = 2)
    private Double precioAlquiler;

    public Transaccion() {
    }

    public Transaccion(Long id, Anuncio anuncio, Usuario comprador, String tipoTransaccion, LocalDate fechaPublicacion,
            LocalDate fechaMovimiento, Integer duracionAlquiler, Double precioAlquiler) {
        this.id = id;
        this.anuncio = anuncio;
        this.comprador = comprador;
        this.tipoTransaccion = tipoTransaccion;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaMovimiento = fechaMovimiento;
        this.duracionAlquiler = duracionAlquiler;
        this.precioAlquiler = precioAlquiler;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Integer getDuracionAlquiler() {
        return duracionAlquiler;
    }

    public void setDuracionAlquiler(Integer duracionAlquiler) {
        this.duracionAlquiler = duracionAlquiler;
    }

    public Double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(Double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
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
        Transaccion other = (Transaccion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Transaccion [id=" + id + ", anuncio=" + anuncio.getId() + ", comprador=" + comprador.getNombre()
                + ", tipoTransaccion="
                + tipoTransaccion + ", fechaPublicacion=" + fechaPublicacion + ", fechaMovimiento=" + fechaMovimiento
                + ", duracionAlquiler=" + duracionAlquiler + ", precioAlquiler=" + precioAlquiler + "]";
    }
}
