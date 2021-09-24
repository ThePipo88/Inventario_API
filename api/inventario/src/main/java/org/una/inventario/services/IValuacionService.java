package org.una.inventario.services;

import org.una.inventario.dto.*;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Inventario;
import org.una.inventario.entities.Valuacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IValuacionService {

    public Optional<List<ValuacionDTO>> findAll();

    public Optional<ValuacionDTO> findById(Long id);

    public Optional<List<ActivoDTO>> findByActivo(Long id);

    public Optional<List<InventarioDTO>> findByInventario(Long id);

    public Optional<List<ValuacionDTO>> findByFechaCreacion(Date startDate);

    public Optional<ValuacionDTO> create(ValuacionDTO valuacionDTO);

    public Optional<ValuacionDTO> update(ValuacionDTO valuacionDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
