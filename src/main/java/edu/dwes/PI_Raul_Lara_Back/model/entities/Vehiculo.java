package edu.dwes.PI_Raul_Lara_Back.model.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long id;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(name = "fecha_fabricacion", nullable = false)
    private LocalDate fecha_fabricacion;

    @Column(nullable = false, length = 30)
    private String tipo; // turismo, furgoneta, moto, etc.

    @Column(length = 20)
    private String combustible;

    @Column(nullable = false)
    private String matricula;

    private Integer kilometraje;

    @Column(name = "precio_estimado")
    private Double precioEstimado;

    @OneToOne(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private Anuncio anuncio;

    public Vehiculo() {
    }

    public Vehiculo(Long id, String marca, String modelo, LocalDate fecha_fabricacion, String tipo, String combustible,
            String matricula,
            Integer kilometraje, Double precioEstimado, Anuncio anuncio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.fecha_fabricacion = fecha_fabricacion;
        this.tipo = tipo;
        this.combustible = combustible;
        this.kilometraje = kilometraje;
        this.precioEstimado = precioEstimado;
        this.anuncio = anuncio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(LocalDate fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Double getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(Double precioEstimado) {
        this.precioEstimado = precioEstimado;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
        Vehiculo other = (Vehiculo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
