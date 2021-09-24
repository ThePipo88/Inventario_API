package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;
>>>>>>> main
import org.una.inventario.dto.RolDTO;
import org.una.inventario.entities.Rol;
import org.una.inventario.entities.Transaccion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
=======
@Repository
>>>>>>> main
public interface IRolRepository extends JpaRepository<Rol, Long> {

    public List<Rol> findByEstado(boolean estado);

    public List<Rol> findByFechaCreacionBetween(Date startDate, Date endDate);

}
