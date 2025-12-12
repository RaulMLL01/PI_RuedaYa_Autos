package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class MensajeAutomaticoDTO {

    private String email;
    private String nombre;
    private String mensaje;

    public MensajeAutomaticoDTO() {
    }

    public MensajeAutomaticoDTO(String email, String nombre, String mensaje) {
        this.email = email;
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
