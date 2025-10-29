package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class MensajeDTO {
    private Long id;
    private Long emisorId;
    private Long receptorId;
    private String contenido;
    private String fechaEnvio;

    public MensajeDTO() {
    }

    public MensajeDTO(Long id, Long emisorId, Long receptorId, String contenido, String fechaEnvio) {
        this.id = id;
        this.emisorId = emisorId;
        this.receptorId = receptorId;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(Long emisorId) {
        this.emisorId = emisorId;
    }

    public Long getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(Long receptorId) {
        this.receptorId = receptorId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

}
