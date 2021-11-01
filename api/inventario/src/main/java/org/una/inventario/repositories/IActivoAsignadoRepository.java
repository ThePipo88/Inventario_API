package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.entities.*;

import java.util.List;

public interface IActivoAsignadoRepository extends JpaRepository<ActivoAsignado, Long> {

    public List<ActivoAsignado> findByUsuario(Long id);

    public List<ActivoAsignado> findByActivo(Long id);

    public List<ActivoAsignado> findByEstado(boolean estado);

}
