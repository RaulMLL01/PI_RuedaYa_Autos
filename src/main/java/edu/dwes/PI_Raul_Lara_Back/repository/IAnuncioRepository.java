package edu.dwes.PI_Raul_Lara_Back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.dwes.PI_Raul_Lara_Back.model.entities.Anuncio;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;

@Repository
public interface IAnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByVendedor(Usuario vendedor);

    List<Anuncio> findByVendidoFalse();

    long countByVendedor(Usuario vendedor);
}
