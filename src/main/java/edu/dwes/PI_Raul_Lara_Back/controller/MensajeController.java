package edu.dwes.PI_Raul_Lara_Back.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dwes.PI_Raul_Lara_Back.model.dto.MensajeDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Mensaje;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.IMensajeService;

@RestController
@RequestMapping("/msg")
public class MensajeController {

    @Autowired
    private IMensajeService service;

    @GetMapping
    public List<MensajeDTO> listarMensaje() {
        return service.findAll()
                .stream()
                .map(DTOConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Mensaje> obtenerMensaje(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Mensaje guardarMensaje(@RequestBody Mensaje Mensaje) {
        return service.save(Mensaje);
    }

    @DeleteMapping("/{id}")
    public void eliminarMensaje(@PathVariable Long id) {
        service.deleteById(id);
    }
}
