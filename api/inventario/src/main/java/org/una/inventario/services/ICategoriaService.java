package org.una.inventario.services;

import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {

    public Optional<List<CategoriaDTO>> findAll();

    public Optional<CategoriaDTO> findById(Long id);

    public Optional<List<CategoriaDTO>> findByNombre(String nombre);

    public Optional<List<CategoriaDTO>> findByEstado(boolean estado);

    public Optional<CategoriaDTO> create(CategoriaDTO categoriaDTO);

    public Optional<CategoriaDTO> update(CategoriaDTO categoriaDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
