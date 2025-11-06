package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Rol;
import edu.dwes.PI_Raul_Lara_Back.repository.IRolRepository;
import edu.dwes.PI_Raul_Lara_Back.service.IRolService;

@Service
public class RolServiceImpl implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Rol save(Rol Rol) {
        return rolRepository.save(Rol);
    }

    @Override
    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }
}
