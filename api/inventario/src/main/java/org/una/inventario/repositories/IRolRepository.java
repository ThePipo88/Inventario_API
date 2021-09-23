package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.entities.Rol;
import org.una.inventario.entities.Transaccion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IRolRepository extends JpaRepository<Rol, Long> {

    public List<Rol> findByEstado(boolean estado);

    public List<Rol> findByFechaCreacionBetween(Date startDate, Date endDate);

}
