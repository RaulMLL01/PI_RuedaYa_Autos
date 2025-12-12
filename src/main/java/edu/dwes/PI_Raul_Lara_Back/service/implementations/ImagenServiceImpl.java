package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.ImagenDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Imagen;
import edu.dwes.PI_Raul_Lara_Back.repository.IAnuncioRepository;
import edu.dwes.PI_Raul_Lara_Back.repository.IImagenRepository;
import edu.dwes.PI_Raul_Lara_Back.service.IImagenService;

@Service
public class ImagenServiceImpl implements IImagenService {

    @Autowired
    private IImagenRepository imagenRepo;

    @Autowired
    private IAnuncioRepository anuncioRepo;

    private final Path uploadPath = Paths.get("uploads");

    @Override
    public ImagenDTO subirImagen(Long idAnuncio, MultipartFile file)
            throws IOException, NonExistentException {

        if (file == null || file.isEmpty()) {
            throw new IOException("Archivo vacío");
        }

        Anuncio anuncio = anuncioRepo.findById(idAnuncio)
                .orElseThrow(() -> new NonExistentException("Anuncio no encontrado"));

        Files.createDirectories(uploadPath);

        String filename = UUID.randomUUID() + "_" +
                StringUtils.cleanPath(file.getOriginalFilename());

        Path target = uploadPath.resolve(filename);

        Files.copy(file.getInputStream(), target,
                StandardCopyOption.REPLACE_EXISTING);

        Imagen img = new Imagen();
        img.setAnuncio(anuncio);
        img.setUrl("/uploads/" + filename);
        img.setEsPrincipal(false);

        imagenRepo.save(img);
        System.out.println("IMAGEN GUARDADA EN: " + target.toAbsolutePath());

        return new ImagenDTO(
                img.getIdImagen(),
                img.getUrl(),
                img.isEsPrincipal());
    }

    @Override
    public void marcarComoPrincipal(Long idAnuncio, Long idImagen) throws NonExistentException {

        Anuncio anuncio = anuncioRepo.findById(idAnuncio)
                .orElse(null);

        if (anuncio == null) {
            throw new NonExistentException("No se pudo encontrar un anuncio donde añadir las imagenes");
        }

        Imagen img = imagenRepo.findById(idImagen)
                .orElse(null);

        if (img == null) {
            throw new NonExistentException("No se pudo encontrar la imagen indicada");
        }

        if (!img.getAnuncio().getId().equals(anuncio.getId()))
            throw new NonExistentException("No se pudo encontrar el anuncio especificado");

        // poner todas a false
        anuncio.getImagenes().forEach(i -> i.setEsPrincipal(false));

        // activar principal
        img.setEsPrincipal(true);

        imagenRepo.save(img);
    }

    @Override
    public void eliminarImagen(Long idAnuncio, Long idImagen) throws NonExistentException {

        Imagen img = imagenRepo.findById(idImagen)
                .orElse(null);

        if (img != null) {
            imagenRepo.delete(img);
        } else {
            return;
        }
    }
}
