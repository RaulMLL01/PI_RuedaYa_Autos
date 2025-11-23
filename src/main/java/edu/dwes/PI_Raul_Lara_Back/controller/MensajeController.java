
package edu.dwes.PI_Raul_Lara_Back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeDTO;
import edu.dwes.PI_Raul_Lara_Back.service.IMensajeService;

@RestController
@RequestMapping("/mensajes")
@CrossOrigin
public class MensajeController {

    @Autowired
    private IMensajeService servicio;

    @GetMapping
    public ResponseEntity<List<MensajeDTO>> list() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(servicio.findById(id));
        } catch (NonExistentException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<MensajeDTO> create(@RequestBody MensajeDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(servicio.enviarMensaje(dto));
        } catch (NonExistentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO> update(@PathVariable Long id, @RequestBody MensajeDTO dto) {
        try {
            return ResponseEntity.ok(servicio.actualizarContenido(id, dto.getContenido()));
        } catch (NonExistentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{email}")
    public ResponseEntity<?> getMensajesUsuario(@PathVariable String email) {
        try {
            List<MensajeDTO> mensajes = servicio.findAllForUser(email);
            return ResponseEntity.ok(mensajes);

        } catch (NonExistentException e) {
            return ResponseEntity.status(404).body("El usuario no existe");
        }
    }

    @GetMapping("/no-leidos/{email}")
    public ResponseEntity<?> countNoLeidos(@PathVariable String email) {
        try {
            Long count = servicio.countMensajesNoLeidos(email);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener no leídos");
        }
    }

}
