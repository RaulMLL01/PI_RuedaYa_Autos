package edu.dwes.PI_Raul_Lara_Back.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.ImagenDTO;
import edu.dwes.PI_Raul_Lara_Back.service.IAnuncioService;
import edu.dwes.PI_Raul_Lara_Back.service.IImagenService;

@RestController
@RequestMapping("/anuncios")
@CrossOrigin(origins = "*")
public class AnuncioController {

    @Autowired
    private IAnuncioService anuncioService;

    @Autowired
    private IImagenService imagenService;

    // =========================================================
    // LISTAR ANUNCIOS
    // =========================================================
    @GetMapping("/listado")
    public ResponseEntity<List<AnuncioDTO>> listarAnuncios() {
        return ResponseEntity.ok(anuncioService.findAllDTO());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<AnuncioDTO>> getDisponibles() {
        return ResponseEntity.ok(anuncioService.findDisponibles());
    }

    // =========================================================
    // OBTENER ANUNCIO POR ID
    // =========================================================
    @GetMapping("/{id}")
    public ResponseEntity<AnuncioDTO> obtenerAnuncio(@PathVariable Long id) {
        try {
            AnuncioDTO dto = anuncioService.findDTOById(id);
            return ResponseEntity.ok(dto);
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // =========================================================
    // CREAR ANUNCIO
    // =========================================================
    @PostMapping
    public ResponseEntity<?> guardarAnuncio(@RequestBody AnuncioDTO dto) {
        try {
            AnuncioDTO saved = anuncioService.createFromDTO(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (NonExistentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    // =========================================================
    // ACTUALIZAR ANUNCIO
    // =========================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAnuncio(
            @PathVariable Long id,
            @RequestBody AnuncioDTO dto) {

        try {
            AnuncioDTO updated = anuncioService.updateFromDTO(id, dto);
            return ResponseEntity.ok(updated);
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // =========================================================
    // ELIMINAR ANUNCIO
    // =========================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAnuncio(@PathVariable Long id) {
        try {
            anuncioService.deleteById(id);
            return ResponseEntity.noContent().build();

        } catch (NonExistentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anuncio no encontrado");

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el anuncio porque tiene datos relacionados.");
        }
    }

    // =========================================================
    // IMÁGENES: SUBIR
    // =========================================================
    @PostMapping("/{id}/imagenes")
    public ResponseEntity<?> subirImagen(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        try {
            ImagenDTO dto = imagenService.subirImagen(id, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);

        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();

        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen.");
        }
    }

    // =========================================================
    // IMÁGENES: MARCAR COMO PRINCIPAL
    // =========================================================
    @PutMapping("/{id}/imagenes/{idImg}/principal")
    public ResponseEntity<Void> marcarComoPrincipal(
            @PathVariable Long id,
            @PathVariable Long idImg) {

        try {
            imagenService.marcarComoPrincipal(id, idImg);
            return ResponseEntity.ok().build();

        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // =========================================================
    // IMÁGENES: ELIMINAR
    // =========================================================
    @DeleteMapping("/{id}/imagenes/{idImg}")
    public ResponseEntity<Void> eliminarImagen(
            @PathVariable Long id,
            @PathVariable Long idImg) {

        try {
            imagenService.eliminarImagen(id, idImg);
            return ResponseEntity.noContent().build();

        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
