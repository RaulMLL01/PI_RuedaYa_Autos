package edu.dwes.PI_Raul_Lara_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Transaccion;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {
}
