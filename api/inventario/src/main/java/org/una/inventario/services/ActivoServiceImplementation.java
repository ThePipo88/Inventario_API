package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.*;
import org.una.inventario.entities.*;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivoServiceImplementation implements IActivoService{

    @Autowired
    private IActivoRepository activoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findAll() {
        List<ActivoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoRepository.findAll(), ActivoDTO.class);
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActivoDTO> findById(Long id) {
        Optional<Activo> activo = activoRepository.findById(id);
        if (activo .isEmpty()) throw new NotFoundInformationException();

        ActivoDTO activoDTO = MapperUtils.DtoFromEntity(activo.get(), ActivoDTO.class);
        return Optional.ofNullable(activoDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByNombre(String nombre) {
        List<Activo> activoList = activoRepository.findByNombre(nombre);
        List<ActivoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoList, ActivoDTO.class);
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByEstado(boolean estado) {
        List<Activo> activoList = activoRepository.findByEstado(estado);
        List<ActivoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoList, ActivoDTO.class);
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByCategoria(Long id) {
        List<Activo> categoriaList = activoRepository.findByCategoria(id);
        List<ActivoDTO> categoriaDTOList = MapperUtils.DtoListFromEntityList(categoriaList, ActivoDTO.class);
        return Optional.ofNullable(categoriaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByMarca(Long id) {
        List<Activo> marcaList = activoRepository.findByMarca(id);
        List<ActivoDTO> marcaDTOList = MapperUtils.DtoListFromEntityList(marcaList, ActivoDTO.class);
        return Optional.ofNullable(marcaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByProveedor(Long proveedor) {
        List<Activo> proveedorList = activoRepository.findByProveedor(proveedor);
        List<ActivoDTO> proveedorDTOList = MapperUtils.DtoListFromEntityList(proveedorList, ActivoDTO.class);
        return Optional.ofNullable(proveedorDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActivoDTO> findByContinente(Long id) {
        Optional<Activo> activo = Optional.ofNullable(activoRepository.findByContinente(id));
        ActivoDTO activoDTO = MapperUtils.DtoFromEntity(activo.get(), ActivoDTO.class);
        return Optional.ofNullable(activoDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByActivoBetweenFecha(Date startDate, Date endDate) {
        List<Activo> activoList = activoRepository.findByActivoBetweenFecha(startDate,endDate);
        List<ActivoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoList, ActivoDTO.class);
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional
    public Optional<ActivoDTO> create(ActivoDTO activoDTO) {
        return Optional.ofNullable(getSavedActivoDTO(activoDTO));
    }

    @Override
    @Transactional
    public Optional<ActivoDTO> update(ActivoDTO activoDTO, Long id) {
        if (activoRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedActivoDTO(activoDTO));
    }

    private ActivoDTO getSavedActivoDTO(ActivoDTO activoDTO) {
        Activo activo = MapperUtils.EntityFromDto(activoDTO, Activo.class);
        Activo activoCreated = activoRepository.save(activo);
        return MapperUtils.DtoFromEntity(activoCreated, ActivoDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        activoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        activoRepository.deleteAll();
    }
}
