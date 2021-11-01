package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.*;

import java.util.List;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta, Long> {

    public List<Alerta> findByActivo(Long id);

    public List<Alerta> findByTipo(String tipo);

    public List<Alerta> findByEstado(boolean estado);

}
