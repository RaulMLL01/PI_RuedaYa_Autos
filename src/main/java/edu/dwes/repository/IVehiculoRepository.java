package edu.dwes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.dwes.model.Vehiculo;

public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long> {
}