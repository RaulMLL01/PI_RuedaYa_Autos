package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class TransaccionDTO {
    private Long id;
    private Long vendedorId;
    private Long compradorId;
    private Long anuncioId;
    private String tipo;
    private String fechaMovimiento;
    private Double precio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Long getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(Long compradorId) {
        this.compradorId = compradorId;
    }

    public Long getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(Long anuncioId) {
        this.anuncioId = anuncioId;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
