package org.una.inventario.repositories;

import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Repository
public interface IActivoRepository extends JpaRepository<Activo, Long> {

    public List<Activo> findByNombre(String nombre);

    public List<Activo> findByEstado(boolean estado);

    public List<Activo> findByCategoria(Long id);

    public List<Activo> findByMarca(Long id);

    public List<Activo> findByProveedor(Long proveedor);

    //@Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe=1 AND d.id=:id")
    public Activo findByContinente(Long id);

    @Query("SELECT u FROM Activo u WHERE u.fechaCreacion >= :startDate AND u.fechaCreacion <= :endDate")
    public List<Activo> findByActivoBetweenFecha(@Param("startDate")Date startDate, @Param("endDate")Date endDate);
}
