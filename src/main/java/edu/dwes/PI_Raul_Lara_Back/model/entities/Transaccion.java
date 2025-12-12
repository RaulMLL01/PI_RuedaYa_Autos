package edu.dwes.PI_Raul_Lara_Back.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vendedor", nullable = false)
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "id_comprador", nullable = false)
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "id_anuncio", nullable = false)
    private Anuncio anuncio;

    @Column(name = "tipo_transaccion")
    private String tipo;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento;

    private Double precio;
    private Integer duracionAlquiler;

    public Transaccion() {
    }

    public Transaccion(Long id, Usuario vendedor, Anuncio anuncio, String tipoTransaccion, Double precio,
            Integer duracionAlquiler) {
        this.id = id;
        this.vendedor = vendedor;
        this.anuncio = anuncio;
        this.tipo = tipoTransaccion;
        this.precio = precio;
        this.duracionAlquiler = duracionAlquiler;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipoTransaccion) {
        this.tipo = tipoTransaccion;
    }

    public Integer getDuracionAlquiler() {
        return duracionAlquiler;
    }

    public void setDuracionAlquiler(Integer duracionAlquiler) {
        this.duracionAlquiler = duracionAlquiler;
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

}
