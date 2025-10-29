package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class VehiculoDTO {

    private Long id;
    private String marca;
    private String modelo;
    private int ano;
    private String tipo;

    public VehiculoDTO() {
    }

    public VehiculoDTO(Long id, String marca, String modelo, int ano, String tipo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.tipo = tipo;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getters & setters
}
