
package edu.dwes.PI_Raul_Lara_Back.controller;

import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;
import edu.dwes.PI_Raul_Lara_Back.service.ITransaccionService;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin
public class TransaccionController {

    private final ITransaccionService service;

    public TransaccionController(ITransaccionService s) {
        this.service = s;
    }

    @GetMapping
    public ResponseEntity<List<TransaccionDTO>> list() {
        return ResponseEntity.ok(service.findAllDTO());
    }

    @GetMapping("/{anuncioId}/{usuarioId}/{fecha}")
    public ResponseEntity<TransaccionDTO> get(@PathVariable Long anuncioId, @PathVariable Long usuarioId,
            @PathVariable String fecha) {
        try {
            return ResponseEntity.ok(service.findDTO(anuncioId, usuarioId, fecha));
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransaccionDTO> create(@RequestBody TransaccionDTO dto) {
        try {
            return ResponseEntity.status(201).body(service.createFromDTO(dto));
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{anuncioId}/{usuarioId}/{fecha}")
    public ResponseEntity<Void> delete(@PathVariable Long anuncioId, @PathVariable Long usuarioId,
            @PathVariable String fecha) {
        service.delete(anuncioId, usuarioId, fecha);
        return ResponseEntity.noContent().build();
    }
}
