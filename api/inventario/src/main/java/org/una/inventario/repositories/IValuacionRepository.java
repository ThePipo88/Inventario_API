package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Inventario;
import org.una.inventario.entities.Rol;
import org.una.inventario.entities.Valuacion;

import java.util.Date;
import java.util.List;

@Repository
public interface IValuacionRepository extends JpaRepository<Valuacion, Long> {

    public List<Valuacion> findByActivo(Long id);

    public List<Valuacion> findByInventario(Long id);

    public List<Valuacion> findByFechaCreacion(Date startDate);

}
