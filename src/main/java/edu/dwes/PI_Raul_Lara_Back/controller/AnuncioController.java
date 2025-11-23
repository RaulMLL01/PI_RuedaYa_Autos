package edu.dwes.PI_Raul_Lara_Back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.service.IAnuncioService;

@RestController
@RequestMapping("/anuncios")
@CrossOrigin(origins = "*")
public class AnuncioController {

    @Autowired
    private IAnuncioService anuncioService;

    @GetMapping("/listado")
    public ResponseEntity<List<AnuncioDTO>> listarAnuncios() {
        return ResponseEntity.ok(anuncioService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnuncioDTO> obtenerAnuncio(@PathVariable Long id) {
        AnuncioDTO dto;
        try {
            dto = anuncioService.findDTOById(id);
            return ResponseEntity.ok(dto);
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<AnuncioDTO> guardarAnuncio(@RequestBody AnuncioDTO dto) {
        AnuncioDTO saved;
        try {
            saved = anuncioService.createFromDTO(dto);
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnuncioDTO> actualizarAnuncio(@PathVariable Long id, @RequestBody AnuncioDTO dto) {
        AnuncioDTO updated;
        try {
            updated = anuncioService.updateFromDTO(id, dto);
            return ResponseEntity.ok(updated);
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAnuncio(@PathVariable Long id) {
        anuncioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}