package edu.dwes.PI_Raul_Lara_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.LoginRequestDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.LoginResponseDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.RegistroDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.UsuarioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.IUsuarioService;
import edu.dwes.PI_Raul_Lara_Back.service.security.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class Authenticator {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private DTOConverter converter;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));

            Usuario usuario = usuarioService.findByEmail(request.getEmail());

            String token = jwtService.generateToken(usuario);
            return ResponseEntity.ok(
                    new LoginResponseDTO(token, usuario.getEmail(), usuario.getRol().getNombre(), usuario.getId()));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        } catch (NonExistentException e) {
            return ResponseEntity.status(401).body("El usuario indicado no existe");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegistroDTO request) {
        try {

            // Comprobaciones básicas
            if (usuarioService.existsByEmail(request.getEmail())) {
                return ResponseEntity.status(400).body("El email ya está registrado");
            }

            if (usuarioService.existsByUsername(request.getUsername())) {
                return ResponseEntity.status(400).body("El nombre de usuario ya está en uso");
            }

            // Crear usuario correctamente
            UsuarioDTO nuevo = usuarioService.createFromDTO(request);

            // Obtener entidad completa para generar token
            Usuario usuario = usuarioService.findByEmail(request.getEmail());

            String token = jwtService.generateToken(usuario);

            return ResponseEntity.ok(
                    new LoginResponseDTO(
                            token,
                            nuevo.getEmail(),
                            nuevo.getRol(),
                            nuevo.getId()));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/check-email")
    public AvailabilityResponse checkEmail(@RequestParam String email) {

        boolean exists = usuarioService.existsByEmail(email);

        return new AvailabilityResponse(
                !exists,
                exists ? "El email ya está en uso" : "Email disponible");
    }

    @GetMapping("/check-username")
    public AvailabilityResponse checkUsername(@RequestParam String username) {

        boolean exists = usuarioService.existsByUsername(username);

        return new AvailabilityResponse(
                !exists,
                exists ? "El username ya está en uso" : "Username disponible");
    }

    public record AvailabilityResponse(boolean available, String message) {
    }

}
