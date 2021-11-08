package org.una.inventario.services;

import org.una.inventario.dto.InventarioDTO;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.entities.Marca;

import java.util.List;
import java.util.Optional;

public interface IMarcaService {

    public Optional<List<MarcaDTO>> findAll();

    public Optional<MarcaDTO> findById(Long id);

    public Optional <MarcaDTO> findByNombre(String nombre);

    public Optional<List<MarcaDTO>> findByEstado(boolean estado);

    public Optional<MarcaDTO> create(MarcaDTO marcaDTO);

    public Optional<MarcaDTO> update(MarcaDTO marcaDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
