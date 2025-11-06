package edu.dwes.PI_Raul_Lara_Back.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.repository.IAnuncioRepository;
import edu.dwes.PI_Raul_Lara_Back.service.IAnuncioService;

@Service
public class AnuncioServiceImpl implements IAnuncioService {

    @Autowired
    private IAnuncioRepository anuncioRepository;

    @Override
    public List<Anuncio> findAll() {
        return this.anuncioRepository.findAll();
    }

    @Override
    public Optional<Anuncio> findById(Long id) {
        return this.anuncioRepository.findById(id);
    }

    @Override
    public Anuncio save(Anuncio a) {
        return this.anuncioRepository.save(a);
    }

    @Override
    public void deleteById(Long id) {
        this.anuncioRepository.deleteById(id);
    }

}
