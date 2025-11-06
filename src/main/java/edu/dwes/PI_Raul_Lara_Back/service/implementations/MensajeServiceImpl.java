package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Mensaje;
import edu.dwes.PI_Raul_Lara_Back.repository.IMensajeRepository;
import edu.dwes.PI_Raul_Lara_Back.service.IMensajeService;

@Service
public class MensajeServiceImpl implements IMensajeService {
    @Autowired
    private IMensajeRepository mensajeRepository;

    @Override
    public List<Mensaje> findAll() {
        return mensajeRepository.findAll();
    }

    @Override
    public Optional<Mensaje> findById(Long id) {
        return mensajeRepository.findById(id);
    }

    @Override
    public Mensaje save(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    @Override
    public void deleteById(Long id) {
        mensajeRepository.deleteById(id);
    }
}
