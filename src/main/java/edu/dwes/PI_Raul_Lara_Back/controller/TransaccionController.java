package edu.dwes.PI_Raul_Lara_Back.controller;

import java.time.LocalDate;
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

import edu.dwes.PI_Raul_Lara_Back.model.dto.TransaccionDTO;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;
import edu.dwes.PI_Raul_Lara_Back.service.DTOConverter;
import edu.dwes.PI_Raul_Lara_Back.service.ITransaccionService;

@RestController
@RequestMapping("/tra")
public class TransaccionController {

    @Autowired
    private ITransaccionService service;

    @GetMapping
    public List<TransaccionDTO> listarTransaccion() {
        return service.findAll()
                .stream()
                .map(DTOConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/usuario/{usuarioId}/producto/{productoId}/fecha/{fecha}")
    public Optional<Transaccion> obtenerTransaccion(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId,
            @PathVariable String fecha) {
        return service.findById(usuarioId, productoId, LocalDate.parse(fecha));
    }

    @PostMapping
    public Transaccion guardarTransaccion(@RequestBody Transaccion transaccion) {
        return service.save(transaccion);
    }

    @DeleteMapping("/usuario/{usuarioId}/producto/{productoId}")
    public void eliminarTransaccion(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId,
            @PathVariable String fecha) {

        service.deleteById(usuarioId, productoId, LocalDate.parse(fecha));
    }
}
