package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Alerta;
import org.una.inventario.entities.Categoria;
import org.una.inventario.entities.Rol;

import java.util.List;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findByNombre(String nombre);

    public List<Categoria> findByEstado(boolean estado);

}
