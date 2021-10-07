package org.una.inventario.services;

import org.una.inventario.dto.*;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.ActivoAsignado;
import org.una.inventario.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IActivoAsignadoService {

    public Optional<List<ActivoAsignadoDTO>> findAll();

    public Optional<ActivoAsignadoDTO> findById(Long id);
    public Optional <List<ActivoAsignadoDTO>> findByUsuario(Long id);

    public Optional<List<ActivoAsignadoDTO>> findByActivo(Long id);

    public Optional<List<ActivoAsignadoDTO>> findByEstado(boolean estado);

    public Optional<ActivoAsignadoDTO> create(ActivoAsignadoDTO activoAignadoDTO);

    public Optional<ActivoAsignadoDTO> update(ActivoAsignadoDTO activoDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
