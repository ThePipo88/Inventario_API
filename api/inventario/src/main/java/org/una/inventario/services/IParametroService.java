package org.una.inventario.services;

import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.dto.ParametroDTO;
import org.una.inventario.entities.Parametro;

import java.util.List;
import java.util.Optional;

public interface IParametroService {

    public Optional<List<ParametroDTO>> findAll();

    public Optional<ParametroDTO> findById(Long id);

    public Optional<List<ParametroDTO>> findByValor(String valor);

    public Optional<List<ParametroDTO>> findByNombre(String nombre);

    public Optional<List<ParametroDTO>> findByEstado(Boolean estado);

    public Optional<ParametroDTO> create(ParametroDTO parametroDTO);

    public Optional<ParametroDTO> update(ParametroDTO parametroDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
