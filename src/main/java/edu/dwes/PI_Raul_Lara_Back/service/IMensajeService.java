package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeDTO;

@Service
public interface IMensajeService {

    List<MensajeDTO> findAll();

    MensajeDTO findById(Long id) throws NonExistentException;

    MensajeDTO enviarMensaje(MensajeDTO dto) throws NonExistentException;

    MensajeDTO actualizarContenido(Long id, String nuevoContenido) throws NonExistentException;

    void eliminar(Long id);

    List<MensajeDTO> mensajesEntreUsuarios(Long idEmisor, Long idReceptor) throws NonExistentException;

    List<MensajeDTO> findAllForUser(String email) throws NonExistentException;

    Long countMensajesNoLeidos(String email);

    void marcarComoLeido(Long id);

}
