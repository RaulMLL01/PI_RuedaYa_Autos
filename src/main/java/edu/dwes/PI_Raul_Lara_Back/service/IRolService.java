package edu.dwes.PI_Raul_Lara_Back.service;

import java.util.List;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.RolDTO;

@Service
public interface IRolService {
    List<RolDTO> findAll();

    RolDTO findById(Long id) throws NonExistentException;

    RolDTO create(RolDTO dto);

    RolDTO update(Long id, RolDTO dto) throws NonExistentException;

    void delete(Long id);

}