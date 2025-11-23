package edu.dwes.PI_Raul_Lara_Back.model.dto;

public class ImagenDTO {
    private Long id;
    private String url;
    private Boolean esPrincipal;

    public ImagenDTO() {
    }

    public ImagenDTO(Long id, String url, Boolean esPrincipal) {
        this.id = id;
        this.url = url;
        this.esPrincipal = esPrincipal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
}