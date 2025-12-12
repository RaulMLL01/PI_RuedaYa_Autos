
package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;

@Service
public interface ITransaccionService {

    // Obtener todas las transacciones en formato DTO
    List<TransaccionDTO> findAllDTO();

    // Obtener una transacción por ID (id_transaccion)
    TransaccionDTO findDTO(Long idTransaccion) throws NonExistentException;

    // Crear una transacción a partir de un DTO
    TransaccionDTO createFromDTO(TransaccionDTO dto) throws NonExistentException;

    // Eliminar una transacción por ID
    void delete(Long idTransaccion);
}
