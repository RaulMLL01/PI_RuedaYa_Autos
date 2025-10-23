package edu.dwes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.dwes.model.Transaccion;

public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {
}
