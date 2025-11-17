package edu.dwes.PI_Raul_Lara_Back.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "imagen")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagen;

    @ManyToOne
    @JoinColumn(name = "id_anuncio", nullable = false)
    private Anuncio anuncio;

    @Column(nullable = false)
    private String url;

    private boolean esPrincipal;

    // Getters y Setters
    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
}
