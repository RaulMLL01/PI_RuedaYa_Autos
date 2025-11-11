package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class TransaccionDTO {
    private Long anuncioId;
    private Long usuarioId;
    private String tipo;
    private String fechaMovimiento;

    public TransaccionDTO() {
    }

    public TransaccionDTO(Long anuncioId, Long usuarioId, String tipo, String fechaMovimiento) {
        this.anuncioId = anuncioId;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.fechaMovimiento = fechaMovimiento;
    }

    public Long getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(Long anuncioId) {
        this.anuncioId = anuncioId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

}
