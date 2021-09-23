package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Usuario;

import java.util.List;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {

    public List<Departamento> findByEstado(Boolean estado);

    public List<Departamento> findByNombreContainingIgnoreCase(String nombre);

}