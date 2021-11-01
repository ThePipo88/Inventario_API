package org.una.inventario.services;

import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Alerta;

import java.util.List;
import java.util.Optional;

public interface IAlertaService {

    public Optional<List<AlertaDTO>> findAll();

    public Optional<AlertaDTO> findById(Long id);

    public Optional<List<AlertaDTO>> findByActivo(Long id);

    public Optional<List<AlertaDTO>> findByTipo(String tipo);

    public Optional<List<AlertaDTO>> findByEstado(boolean estado);

    public Optional<AlertaDTO> create(AlertaDTO alertaDTO);

    public Optional<AlertaDTO> update(AlertaDTO alertaDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
