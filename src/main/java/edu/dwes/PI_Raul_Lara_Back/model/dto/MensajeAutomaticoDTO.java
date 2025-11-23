package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class MensajeAutomaticoDTO {

    private String para;
    private String asunto;
    private String cuerpo;

    public MensajeAutomaticoDTO() {
    }

    public MensajeAutomaticoDTO(String para, String asunto, String cuerpo) {
        this.para = para;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

}
