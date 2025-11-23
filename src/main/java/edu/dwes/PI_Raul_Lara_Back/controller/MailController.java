package edu.dwes.PI_Raul_Lara_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeAutomaticoDTO;
import edu.dwes.PI_Raul_Lara_Back.service.EmailService;

@RestController
@RequestMapping("/mail")
@CrossOrigin
public class MailController {

    @Autowired
    private EmailService mailService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarCorreo(@RequestBody MensajeAutomaticoDTO dto) {

        if (dto.getPara() == null || dto.getPara().isBlank()) {
            return ResponseEntity.badRequest().body("El campo 'para' es obligatorio.");
        }
        if (dto.getAsunto() == null || dto.getAsunto().isBlank()) {
            return ResponseEntity.badRequest().body("El campo 'asunto' es obligatorio.");
        }
        if (dto.getCuerpo() == null || dto.getCuerpo().isBlank()) {
            return ResponseEntity.badRequest().body("El campo 'cuerpo' es obligatorio.");
        }

        try {
            mailService.send(dto.getPara(), dto.getAsunto(), dto.getCuerpo());
            return ResponseEntity.ok("Correo enviado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error enviando correo: " + e.getMessage());
        }
    }
}
