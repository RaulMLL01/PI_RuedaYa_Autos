package edu.dwes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.dwes.model.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
