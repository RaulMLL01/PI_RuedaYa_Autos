package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;
import edu.dwes.PI_Raul_Lara_Back.model.entities.TransaccionId;
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
    public Optional<Transaccion> findById(Long idv, Long ida, LocalDate fecha) {
        TransaccionId busqueda = new TransaccionId(idv, ida, fecha);
        return transaccionRepository.findById(busqueda);
    }

    @Override
    public Transaccion save(Transaccion Transaccion) {
        return transaccionRepository.save(Transaccion);
    }

    @Override
    public void deleteById(Long idv, Long ida, LocalDate fecha) {
        TransaccionId busqueda = new TransaccionId(idv, ida, fecha);
        transaccionRepository.deleteById(busqueda);
    }

}
