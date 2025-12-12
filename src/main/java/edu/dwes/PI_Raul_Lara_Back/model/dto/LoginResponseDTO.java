package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class LoginResponseDTO {

    private String token;
    private String email;
    private String rol;
    private Long userId;

    public LoginResponseDTO(String token, String email, String rol, Long userId) {
        this.token = token;
        this.email = email;
        this.rol = rol;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
