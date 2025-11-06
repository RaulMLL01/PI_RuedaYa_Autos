package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;
import edu.dwes.PI_Raul_Lara_Back.repository.ITransaccionRepository;
import edu.dwes.PI_Raul_Lara_Back.service.ITransaccionService;

@Service
public class TransaccionServiceImpl implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    public List<Transaccion> findAll() {
        return transaccionRepository.findAll();
    }

    @Override
    public Optional<Transaccion> findById(Long id) {
        return transaccionRepository.findById(id);
    }

    @Override
    public Transaccion save(Transaccion Transaccion) {
        return transaccionRepository.save(Transaccion);
    }

    @Override
    public void deleteById(Long id) {
        transaccionRepository.deleteById(id);
    }

}
