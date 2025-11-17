package edu.dwes.PI_Raul_Lara_Back.model.dto;

import java.util.ArrayList;
import java.util.List;

public class AnuncioDTO {
    private Long id;
    private String fechaPublicacion;
    private String estado;
    private List<String> imagenes;
    private VehiculoDTO vehiculo;

    public AnuncioDTO() {
    }

    public AnuncioDTO(Long id, String fechaPublicacion, String estado, VehiculoDTO vehiculo) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
        this.imagenes = new ArrayList<>();
        this.vehiculo = vehiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

}
