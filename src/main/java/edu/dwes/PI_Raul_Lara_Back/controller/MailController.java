package edu.dwes.PI_Raul_Lara_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeAutomaticoDTO;
import edu.dwes.PI_Raul_Lara_Back.service.EmailService;

@RestController
@RequestMapping("/mail")
@CrossOrigin
public class MailController {

    @Autowired
    private EmailService mailService;

    @Value("${app.mail.admin}")
    private String adminEmail;

    @PostMapping("/contacto")
    public ResponseEntity<String> enviarCorreo(@RequestBody MensajeAutomaticoDTO dto) {

        System.out.println(">>> ENTRO");

        try {

            // 1️⃣ Enviar correo al usuario RuedaYA.01
            String paraUsuario = dto.getEmail();
            String asuntoUsuario = "Gracias por contactar con RuedaYa";
            String cuerpoUsuario = "Hola " + dto.getNombre() + ",\n\n" +
                    "Hemos recibido tu mensaje:\n\n" +
                    dto.getMensaje() + "\n\n" +
                    "Te responderemos pronto.\nRuedaYa.";

            mailService.send(paraUsuario, asuntoUsuario, cuerpoUsuario);

            // 2️⃣ Enviar correo al administrador
            String paraAdmin = "contacto@ruedaya.com";
            String asuntoAdmin = "Nuevo mensaje recibido";
            String cuerpoAdmin = "Nombre: " + dto.getNombre() + "\n" +
                    "Email: " + dto.getEmail() + "\n\n" +
                    "Mensaje:\n" + dto.getMensaje();

            mailService.send(paraAdmin, asuntoAdmin, cuerpoAdmin);

            return ResponseEntity.ok("Correo enviado con éxito.");

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 ahora verás el error si sucede
            return ResponseEntity.status(500).body("Error enviando correo");
        }
    }

}
