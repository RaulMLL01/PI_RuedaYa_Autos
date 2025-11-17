package edu.dwes.PI_Raul_Lara_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import edu.dwes.PI_Raul_Lara_Back.model.dto.LoginRequestDTO;
import edu.dwes.PI_Raul_Lara_Back.model.dto.LoginResponseDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));

            Usuario usuario = usuarioService.findByEmail(request.getEmail());

            String token = jwtService.generateToken(usuario);

            return ResponseEntity.ok(
                    new LoginResponseDTO(token, usuario.getEmail(), usuario.getRol().getNombre()));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
