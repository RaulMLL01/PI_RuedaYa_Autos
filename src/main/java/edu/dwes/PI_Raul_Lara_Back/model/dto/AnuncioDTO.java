package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class AnuncioDTO {
    private Long id;
    private Long usuarioId;
    private Long vehiculoId;
    private String fechaPublicacion;
    private String estado;

    public AnuncioDTO() {
    }

    public AnuncioDTO(Long id, Long usuarioId, Long vehiculoId, String fechaPublicacion, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.vehiculoId = vehiculoId;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
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

    // Getters, setters y constructor

}
