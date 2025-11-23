package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.dto.RolDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Rol;
import edu.dwes.PI_Raul_Lara_Back.repository.IRolRepository;
import edu.dwes.PI_Raul_Lara_Back.service.IRolService;

@Service
public class RolServiceImpl implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<RolDTO> findAll() {
        return rolRepository.findAll()
                .stream()
                .map(r -> new RolDTO(r.getId(), r.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public RolDTO findById(Long id) throws NonExistentException {
        Rol r = rolRepository.findById(id)
                .orElseThrow(() -> new NonExistentException("El rol no existe"));
        return new RolDTO(r.getId(), r.getNombre());
    }

    @Override
    public RolDTO create(RolDTO dto) {

        if (rolRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe un rol con ese nombre");
        }

        Rol r = new Rol();
        r.setNombre(dto.getNombre());

        Rol saved = rolRepository.save(r);
        return new RolDTO(saved.getId(), saved.getNombre());
    }

    @Override
    public RolDTO update(Long id, RolDTO dto) throws NonExistentException {

        Rol r = rolRepository.findById(id)
                .orElseThrow(() -> new NonExistentException("El rol no existe"));

        // Solo se actualiza el nombre
        r.setNombre(dto.getNombre());

        Rol saved = rolRepository.save(r);
        return new RolDTO(saved.getId(), saved.getNombre());
    }

    @Override
    public void delete(Long id) {

        if (!rolRepository.existsById(id)) {
            return;
        }

        rolRepository.deleteById(id);
    }
}
