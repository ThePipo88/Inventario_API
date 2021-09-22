package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.entities.*;

import java.util.List;

public interface IActivoAsignadoRepository extends JpaRepository<ActivoAsignado, Long> {

    public Usuario findByUsuario(Long id);

    public List<Activo> findByActivo(Long id);

    public List<ActivoAsignado> findByEstado(boolean estado);

}
