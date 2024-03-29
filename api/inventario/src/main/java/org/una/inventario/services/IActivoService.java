package org.una.inventario.services;

import org.springframework.data.repository.query.Param;
import org.una.inventario.dto.*;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Categoria;
import org.una.inventario.entities.Marca;
import org.una.inventario.entities.Proveedor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IActivoService {

    public Optional<List<ActivoDTO>> findAll();

    public Optional<ActivoDTO> findById(Long id);

    public Optional<List<ActivoDTO>> findByNombre(String nombre);

    public Optional<List<ActivoDTO>> findByEstado(boolean estado);

    public Optional<List<ActivoDTO>> findByCategoria(Long id);

    public Optional<List<ActivoDTO>> findByMarca(Long id);

    public Optional<List<ActivoDTO>> findByProveedor(Long proveedor);

    public Optional<ActivoDTO> findByContinente(Long id);

    public Optional<List<ActivoDTO>> findByActivoBetweenFecha(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

    public Optional<ActivoDTO> create(ActivoDTO activoDTO);

    public Optional<ActivoDTO> update(ActivoDTO activoDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
