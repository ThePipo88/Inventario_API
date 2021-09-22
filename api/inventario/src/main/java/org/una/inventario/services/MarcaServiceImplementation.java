package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.InventarioDTO;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.entities.Categoria;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Marca;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IInventarioRepository;
import org.una.inventario.repositories.IMarcaRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImplementation implements IMarcaService{

    @Autowired
    private IMarcaRepository marcaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<MarcaDTO>> findAll() {
        List<MarcaDTO> marcaDTOList = MapperUtils.DtoListFromEntityList(marcaRepository.findAll(), MarcaDTO.class);
        return Optional.ofNullable(marcaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MarcaDTO> findById(Long id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isEmpty()) throw new NotFoundInformationException();

        MarcaDTO marcaDTO = MapperUtils.DtoFromEntity(marca.get(), MarcaDTO.class);
        return Optional.ofNullable(marcaDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<MarcaDTO>> findByNombre(String nombre) {
        List<Marca> marcaList = marcaRepository.findByNombre(nombre);
        List<MarcaDTO> marcaDTOList = MapperUtils.DtoListFromEntityList(marcaList, MarcaDTO.class);
        return Optional.ofNullable(marcaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<MarcaDTO>> findByEstado(boolean estado) {
        List<Marca> marcaList = marcaRepository.findByEstado(estado);
        List<MarcaDTO> marcaDTOList = MapperUtils.DtoListFromEntityList(marcaList, MarcaDTO.class);
        return Optional.ofNullable(marcaDTOList);
    }

    @Override
    @Transactional
    public Optional<MarcaDTO> create(MarcaDTO marcaDTO) {
        return Optional.ofNullable(getSavedMarcaDTO(marcaDTO));
    }

    @Override
    @Transactional
    public Optional<MarcaDTO> update(MarcaDTO marcaDTO, Long id) {
        if (marcaRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedMarcaDTO(marcaDTO));
    }

    private MarcaDTO getSavedMarcaDTO(MarcaDTO marcaDTO) {
        Marca marca = MapperUtils.EntityFromDto(marcaDTO, Marca.class);
        Marca marcaCreated = marcaRepository.save(marca);
        return MapperUtils.DtoFromEntity(marcaCreated, MarcaDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        marcaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        marcaRepository.deleteAll();
    }
}
