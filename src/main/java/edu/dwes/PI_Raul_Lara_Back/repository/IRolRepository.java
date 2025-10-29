package edu.dwes.PI_Raul_Lara_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
