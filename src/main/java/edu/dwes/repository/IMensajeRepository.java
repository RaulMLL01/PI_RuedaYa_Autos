package edu.dwes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.dwes.model.Mensaje;

public interface IMensajeRepository extends JpaRepository<Mensaje, Long> {
}
