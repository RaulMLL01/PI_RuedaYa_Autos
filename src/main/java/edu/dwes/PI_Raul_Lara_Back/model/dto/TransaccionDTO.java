package edu.dwes.PI_Raul_Lara_Back.model.dto;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;

public class TransaccionDTO {
    private Long anuncioId;
    private Long usuarioId;
    private String tipo;
    private String fechaMovimiento;

    public TransaccionDTO() {
    }

    public TransaccionDTO(Transaccion t) {
        this.anuncioId = t.getAnuncio().getId();
        this.usuarioId = t.getVendedor().getId();
        this.tipo = t.getTipoTransaccion();
        this.fechaMovimiento = t.getFechaMovimiento().toString();
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