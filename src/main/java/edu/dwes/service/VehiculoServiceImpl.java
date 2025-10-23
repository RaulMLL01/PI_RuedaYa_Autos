package edu.dwes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dwes.model.Vehiculo;
import edu.dwes.repository.IVehiculoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private final IVehiculoRepository vehiculoRepository;

    public VehiculoServiceImpl(IVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return vehiculoRepository.findById(id);
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void deleteById(Long id) {
        vehiculoRepository.deleteById(id);
    }
}
