package edu.dwes.PI_Raul_Lara_Back.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.ImagenDTO;

public interface IImagenService {
    ImagenDTO subirImagen(Long idAnuncio, MultipartFile file) throws IOException, NonExistentException;

    void marcarComoPrincipal(Long idAnuncio, Long idImagen) throws NonExistentException;

    void eliminarImagen(Long idAnuncio, Long idImagen) throws NonExistentException;
}
