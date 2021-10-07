package org.una.inventario.services;

import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.dto.ContratoGarantiaDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.ContratoGarantia;

import java.util.List;
import java.util.Optional;

public interface IContratoGarantiaService {

    public Optional<List<ContratoGarantiaDTO>> findAll();

    public Optional<ContratoGarantiaDTO> findById(Long id);

    public Optional<List<ContratoGarantiaDTO>> findByActivo(Long id);

    public Optional<List<ContratoGarantiaDTO>> findByEstado(boolean estado);

    public Optional<List<ContratoGarantiaDTO>> findByCosto(double costo);

    public Optional<ContratoGarantiaDTO> create(ContratoGarantiaDTO contratoGarantiaDTO);

    public Optional<ContratoGarantiaDTO> update(ContratoGarantiaDTO contratoGarantiaDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
