package edu.dwes.PI_Raul_Lara_Back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.RegistroDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.UsuarioDTO;
import edu.dwes.PI_Raul_Lara_Back.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.findDTOById(id));
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody RegistroDTO dto) {
        usuarioService.createFromDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("success", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        try {
            return ResponseEntity.ok(usuarioService.updateFromDTO(id, dto));
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> getPerfil(@PathVariable String email) {
        try {
            return ResponseEntity.ok(usuarioService.findPerfilByEmail(email));
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/anuncios")
    public ResponseEntity<?> getAnunciosByUsuarioId(@PathVariable Long id) {
        try {
            List<AnuncioDTO> lista = usuarioService.findAnunciosByUsuario(id);
            return ResponseEntity.ok(lista);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener los anuncios: " + e.getMessage());
        }
    }

}