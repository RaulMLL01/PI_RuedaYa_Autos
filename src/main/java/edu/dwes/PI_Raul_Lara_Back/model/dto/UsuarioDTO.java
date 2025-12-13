package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class UsuarioDTO {
    private Long id;
    private String username;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String fechaRegistro;
    private String rol;
    private long totalAnuncios;
    private long totalMensajes;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String username, String nombre, String apellidos, String email, String telefono,
            String fechaRegistro,
            String rol) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
        this.apellidos = apellidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public long getTotalAnuncios() {
        return totalAnuncios;
    }

    public void setTotalAnuncios(long totalAnuncios) {
        this.totalAnuncios = totalAnuncios;
    }

    public long getTotalMensajes() {
        return totalMensajes;
    }

    public void setTotalMensajes(long totalMensajes) {
        this.totalMensajes = totalMensajes;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}