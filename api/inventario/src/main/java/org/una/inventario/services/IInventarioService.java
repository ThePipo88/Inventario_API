package org.una.inventario.services;

import org.una.inventario.dto.ContratoGarantiaDTO;
import org.una.inventario.dto.InventarioDTO;
import org.una.inventario.entities.Inventario;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IInventarioService {

    public Optional<List<InventarioDTO>> findAll();

    public Optional<InventarioDTO> findById(Long id);

    public Optional<List<InventarioDTO>> findByEstado(boolean estado);

    public Optional<List<InventarioDTO>> findByFechaCreacion(Date startDate);

    public Optional<InventarioDTO> create(InventarioDTO inventarioDTO);

    public Optional<InventarioDTO> update(InventarioDTO inventarioDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
