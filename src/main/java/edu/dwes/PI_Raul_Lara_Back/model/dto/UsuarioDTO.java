package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class UsuarioDTO {

    private Long id;
    private String username;
    private String email;
    private String telefono;
    private String fechaRegistro;
    private String rolName;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String username, String email, String telefono, String fechaRegistro,
            String rolName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.rolName = rolName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getrolName() {
        return rolName;
    }

    public void setrolName(String rolName) {
        this.rolName = rolName;
    }
}
