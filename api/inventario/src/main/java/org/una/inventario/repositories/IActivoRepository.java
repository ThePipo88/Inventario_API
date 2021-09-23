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

    public List<Categoria> findByCategoria(Long id);

    public List<Marca> findByMarca(Long id);

    public List<Proveedor> findByProveedor(Long proveedor);

    //@Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe=1 AND d.id=:id")
    public Activo findByContinente(Long id);

}