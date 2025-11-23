package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class VehiculoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String fechaFabricacion;
    private String tipo;
    private Double precioEstimado;

    public VehiculoDTO() {
    }

    public VehiculoDTO(Long id, String marca, String modelo, String fechaFabricacion, String tipo,
            Double precioEstimado) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaFabricacion = fechaFabricacion;
        this.tipo = tipo;
        this.precioEstimado = precioEstimado;
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

    public String getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(String fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(Double precioEstimado) {
        this.precioEstimado = precioEstimado;
    }
}