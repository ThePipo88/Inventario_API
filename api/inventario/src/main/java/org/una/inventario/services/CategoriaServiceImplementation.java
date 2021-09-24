package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Alerta;
import org.una.inventario.entities.Categoria;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IAlertaRepository;
import org.una.inventario.repositories.ICategoriaRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaServiceImplementation implements ICategoriaService{

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categoriaDTOList = MapperUtils.DtoListFromEntityList(categoriaRepository.findAll(), CategoriaDTO.class);
        return Optional.ofNullable(categoriaDTOList );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaDTO> findById(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) throw new NotFoundInformationException();

        CategoriaDTO categoriaDTO = MapperUtils.DtoFromEntity(categoria.get(), CategoriaDTO.class);
        return Optional.ofNullable(categoriaDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CategoriaDTO>> findByNombre(String nombre) {
        List<Categoria> categoriaList = categoriaRepository.findByNombre(nombre);
        List<CategoriaDTO> categoriaDTOList = MapperUtils.DtoListFromEntityList(categoriaList, CategoriaDTO.class);
        return Optional.ofNullable(categoriaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CategoriaDTO>> findByEstado(boolean estado) {
        List<Categoria> tipoList = categoriaRepository.findByEstado(estado);
        List<CategoriaDTO> tipoDTOList = MapperUtils.DtoListFromEntityList(tipoList, CategoriaDTO.class);
        return Optional.ofNullable(tipoDTOList);
    }

    @Override
    @Transactional
    public Optional<CategoriaDTO> create(CategoriaDTO categoriaDTO) {
        return Optional.ofNullable(getSavedCategoriaDTO(categoriaDTO));
    }

    @Override
    @Transactional
    public Optional<CategoriaDTO> update(CategoriaDTO categoriaDTO, Long id) {
        if (categoriaRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedCategoriaDTO(categoriaDTO));
    }

    private CategoriaDTO getSavedCategoriaDTO(CategoriaDTO categoriaDTO) {
        Categoria categoria = MapperUtils.EntityFromDto(categoriaDTO, Categoria.class);
        Categoria categoriaCreated = categoriaRepository.save(categoria);
        return MapperUtils.DtoFromEntity(categoriaCreated, CategoriaDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        categoriaRepository.deleteAll();
    }
}
