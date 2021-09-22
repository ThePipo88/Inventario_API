package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.ContratoGarantia;
import org.una.inventario.entities.Inventario;
import org.una.inventario.entities.Rol;

import java.util.Date;
import java.util.List;

@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, Long> {

    public List<Inventario> findByEstado(boolean estado);

    public List<Inventario> findByFechaCreacion(Date startDate);
}
