package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.*;
import org.una.inventario.entities.*;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoAsignadoRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ActivoAsignadoServiceImplementation implements IActivoAsignadoService {

    @Autowired
    private IActivoAsignadoRepository activoAsignadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findAll() {
        List<ActivoAsignadoDTO> activoAsignadoDTOList = MapperUtils.DtoListFromEntityList(activoAsignadoRepository.findAll(), ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoAsignadoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActivoAsignadoDTO> findById(Long id) {
        Optional<ActivoAsignado> activoAsignado = activoAsignadoRepository.findById(id);
        if (activoAsignado.isEmpty()) throw new NotFoundInformationException();

        ActivoAsignadoDTO activoAsignadoDTO = MapperUtils.DtoFromEntity(activoAsignado.get(), ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoAsignadoDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findByUsuario(Long id) {
        List<ActivoAsignado> activoList = activoAsignadoRepository.findByUsuario(id);
        List<ActivoAsignadoDTO> activoAsignado = MapperUtils.DtoListFromEntityList(activoList , ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoAsignado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findByActivo(Long id) {
        List<ActivoAsignado> activoList = activoAsignadoRepository.findByActivo(id);
        List<ActivoAsignadoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoList, ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoDTOList);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findByEstado(boolean estado) {
        List<ActivoAsignado> estadoList = activoAsignadoRepository.findByEstado(estado);
        List<ActivoAsignadoDTO> estadoDTOList = MapperUtils.DtoListFromEntityList(estadoList, ActivoAsignadoDTO.class);
        return Optional.ofNullable(estadoDTOList);
    }

    @Override
    @Transactional
    public Optional<ActivoAsignadoDTO> create(ActivoAsignadoDTO activoAignadoDTO) {
        return Optional.ofNullable(getSavedActivoAsignadoDTO(activoAignadoDTO));
    }

    @Override
    @Transactional
    public Optional<ActivoAsignadoDTO> update(ActivoAsignadoDTO activoAsignadoDTO, Long id) {
        if (activoAsignadoRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedActivoAsignadoDTO(activoAsignadoDTO));
    }


    private ActivoAsignadoDTO getSavedActivoAsignadoDTO(ActivoAsignadoDTO activoAsignadoDTO) {
        ActivoAsignado activoAsignado = MapperUtils.EntityFromDto(activoAsignadoDTO, ActivoAsignado.class);
        ActivoAsignado activoAsignadoCreated = activoAsignadoRepository.save(activoAsignado);
        return MapperUtils.DtoFromEntity(activoAsignadoCreated, ActivoAsignadoDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        activoAsignadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        activoAsignadoRepository.deleteAll();
    }
}
