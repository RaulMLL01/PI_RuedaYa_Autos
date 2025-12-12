
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

    public TransaccionController(ITransaccionService service) {
        this.service = service;
    }

    // ==========================================================
    // LISTAR TODAS LAS TRANSACCIONES
    // ==========================================================
    @GetMapping
    public ResponseEntity<List<TransaccionDTO>> list() {
        return ResponseEntity.ok(service.findAllDTO());
    }

    // ==========================================================
    // OBTENER UNA TRANSACCIÓN POR ID
    // ==========================================================
    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDTO> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findDTO(id));
        } catch (NonExistentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ==========================================================
    // CREAR UNA TRANSACCIÓN
    // ==========================================================
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransaccionDTO dto) {
        try {
            TransaccionDTO creada = service.createFromDTO(dto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(creada);

        } catch (NonExistentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Datos inválidos: " + e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la transacción.");
        }
    }

    // ==========================================================
    // ELIMINAR UNA TRANSACCIÓN POR ID
    // ==========================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
