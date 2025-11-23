package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.UsuarioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Rol;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;
import edu.dwes.PI_Raul_Lara_Back.repository.IRolRepository;
import edu.dwes.PI_Raul_Lara_Back.repository.IUsuarioRepository;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.IUsuarioService;
import edu.dwes.PI_Raul_Lara_Back.service.security.UsuarioDetails;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepo;
    @Autowired
    private IRolRepository rolRepo;
    @Autowired
    private DTOConverter converter;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepo.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public List<UsuarioDTO> findAllDTO() {
        return findAll().stream().map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO findDTOById(Long id) throws NonExistentException {
        Usuario u = usuarioRepo.findById(id).orElseThrow(() -> new NonExistentException("Usuario no encontrado"));
        return converter.toDTO(u);
    }

    @Override
    @Transactional
    public UsuarioDTO createFromDTO(UsuarioDTO dto) throws IllegalArgumentException {

        Usuario u = new Usuario();

        // Datos básicos
        u.setUsername(dto.getUsername());
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setTelefono(dto.getTelefono());

        // Fecha de registro SIEMPRE es ahora
        u.setFechaRegistro(LocalDate.now());

        // Rol por defecto: USUARIO
        Rol rolUsuario = rolRepo.findByNombre("USUARIO");

        u.setRol(rolUsuario);

        // Si manejas contraseñas aquí:
        if (dto.getPassword() != null) {
            u.setPassword(encoder.encode(dto.getPassword()));
        } else {
            throw new IllegalArgumentException("La contraseña no puede ser null");
        }

        Usuario saved = usuarioRepo.save(u);
        return converter.toDTO(saved);
    }

    @Override
    @Transactional
    public UsuarioDTO updateFromDTO(Long id, UsuarioDTO dto) throws NonExistentException {
        Usuario u = usuarioRepo.findById(id).orElseThrow(() -> new NonExistentException("Usuario no encontrado"));
        u.setUsername(dto.getUsername());
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setTelefono(dto.getTelefono());
        usuarioRepo.save(u);
        return converter.toDTO(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        return new UsuarioDetails(user); // devuelve un único rol
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepo.findByEmail(email).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepo.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepo.existsByUsername(username);
    }

}