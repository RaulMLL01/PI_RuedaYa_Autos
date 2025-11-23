
package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;

@Service
public interface ITransaccionService {

    List<TransaccionDTO> findAllDTO();

    TransaccionDTO findDTO(Long anuncioId, Long usuarioId, String fecha) throws NonExistentException;

    TransaccionDTO createFromDTO(TransaccionDTO dto) throws NonExistentException;

    void delete(Long anuncioId, Long usuarioId, String fecha);
}
