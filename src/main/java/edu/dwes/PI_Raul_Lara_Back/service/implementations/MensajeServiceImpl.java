package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Mensaje;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;
import edu.dwes.PI_Raul_Lara_Back.repository.IMensajeRepository;
import edu.dwes.PI_Raul_Lara_Back.repository.IUsuarioRepository;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.IMensajeService;
import jakarta.transaction.Transactional;

@Service
public class MensajeServiceImpl implements IMensajeService {

    @Autowired
    private IMensajeRepository mensajeRepository;

    @Autowired
    private IUsuarioRepository repo;

    @Autowired
    private DTOConverter converter;

    @Override
    public List<MensajeDTO> findAll() {
        return mensajeRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MensajeDTO findById(Long id) throws NonExistentException {
        Mensaje m = mensajeRepository.findById(id)
                .orElseThrow(() -> new NonExistentException("El mensaje no existe"));
        return converter.toDTO(m);
    }

    @Override
    @Transactional
    public MensajeDTO enviarMensaje(MensajeDTO dto) throws NonExistentException {

        // --- VALIDACIONES ---
        if (dto.getEmisorId() == null) {
            throw new IllegalArgumentException("El ID del emisor no puede ser nulo");
        }

        if (dto.getReceptorId() == null) {
            throw new IllegalArgumentException("El ID del receptor no puede ser nulo");
        }

        if (dto.getContenido() == null || dto.getContenido().trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido del mensaje no puede estar vacío");
        }

        // --- OBTENER USUARIOS ---
        Usuario emisor = repo.findById(dto.getEmisorId())
                .orElseThrow(() -> new NonExistentException("Emisor no encontrado"));

        Usuario receptor = repo.findById(dto.getReceptorId())
                .orElseThrow(() -> new NonExistentException("Receptor no encontrado"));

        // --- CREAR MENSAJE ---
        Mensaje m = new Mensaje();
        m.setEmisor(emisor);
        m.setReceptor(receptor);
        m.setContenido(dto.getContenido());
        m.setFechaEnvio(LocalDateTime.now());
        m.setLeido(false);

        return converter.toDTO(mensajeRepository.save(m));
    }

    @Override
    @Transactional
    public MensajeDTO actualizarContenido(Long id, String nuevoContenido) throws NonExistentException {
        Mensaje m = mensajeRepository.findById(id)
                .orElseThrow(() -> new NonExistentException("Mensaje no encontrado"));

        m.setContenido(nuevoContenido);
        return converter.toDTO(mensajeRepository.save(m));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!mensajeRepository.existsById(id)) {
            return;
        }
        mensajeRepository.deleteById(id);
    }

    @Override
    public List<MensajeDTO> mensajesEntreUsuarios(Long idEmisor, Long idReceptor) {

        List<Mensaje> mensajes = mensajeRepository.findConversacion(idEmisor, idReceptor);

        return mensajes.stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MensajeDTO> findAllForUser(String email) throws NonExistentException {
        Usuario usuario = repo.findByEmail(email)
                .orElseThrow(() -> new NonExistentException("Usuario no encontrado"));

        List<Mensaje> mensajes = mensajeRepository.findAllByUsuario(usuario);

        return mensajes.stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    public Long countMensajesNoLeidos(String email) {
        return mensajeRepository.countByReceptorEmailAndLeidoFalse(email);
    }

    @Override
    @Transactional
    public void marcarComoLeido(Long id) {
        Mensaje m = null;
        try {
            m = mensajeRepository.findById(id)
                    .orElseThrow(() -> new NonExistentException("Mensaje no encontrado"));
        } catch (NonExistentException e) {
            e.printStackTrace();
            return;
        }

        m.setLeido(true);
        mensajeRepository.save(m);
    }

}
