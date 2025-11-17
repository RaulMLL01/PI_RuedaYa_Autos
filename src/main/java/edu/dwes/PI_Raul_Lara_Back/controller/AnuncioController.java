package edu.dwes.PI_Raul_Lara_Back.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dwes.PI_Raul_Lara_Back.model.dto.AnuncioDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.IAnuncioService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private IAnuncioService AnuncioService;

    @GetMapping
    public List<AnuncioDTO> listarAnuncios() {
        return AnuncioService.findAll()
                .stream()
                .map(DTOConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Anuncio> obtenerAnuncio(@PathVariable Long id) {
        return AnuncioService.findById(id);
    }

    @PostMapping
    public Anuncio guardarAnuncio(@RequestBody Anuncio Anuncio) {
        return AnuncioService.save(Anuncio);
    }

    @DeleteMapping("/{id}")
    public void eliminarAnuncio(@PathVariable Long id) {
        AnuncioService.deleteById(id);
    }

}
