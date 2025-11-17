package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class VehiculoDTO {

    private Long id;
    private String marca;
    private String modelo;
    private String fecha;
    private String tipo;
    private double precio_estimado;

    public VehiculoDTO() {
    }

    public VehiculoDTO(Long id, String marca, String modelo, String fecha, String tipo, double precio_estimado) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.fecha = fecha;
        this.tipo = tipo;
        this.precio_estimado = precio_estimado;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio_estimado() {
        return precio_estimado;
    }

    public void setPrecio_estimado(double precio_estimado) {
        this.precio_estimado = precio_estimado;
    }

}
