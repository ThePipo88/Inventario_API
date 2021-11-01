package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Alerta;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoRepository;
import org.una.inventario.repositories.IAlertaRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Repository
public class AlertaServiceImplementation implements IAlertaService{

    @Autowired
    private IAlertaRepository alertaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findAll() {
        List<AlertaDTO> alertaDTOList = MapperUtils.DtoListFromEntityList(alertaRepository.findAll(), AlertaDTO.class);
        return Optional.ofNullable(alertaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlertaDTO> findById(Long id) {
        Optional<Alerta> alerta = alertaRepository.findById(id);
        if (alerta.isEmpty()) throw new NotFoundInformationException();

        AlertaDTO alertaDTO = MapperUtils.DtoFromEntity(alerta.get(), AlertaDTO.class);
        return Optional.ofNullable(alertaDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findByActivo(Long id) {
        List<Alerta> activo = alertaRepository.findByActivo(id);
        List<AlertaDTO> activoDTO = MapperUtils.DtoListFromEntityList(activo, AlertaDTO.class);
        return Optional.ofNullable(activoDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findByTipo(String tipo) {
        List<Alerta> alertaList = alertaRepository.findByTipo(tipo);
        List<AlertaDTO> activoDTOList = MapperUtils.DtoListFromEntityList(alertaList, AlertaDTO.class);
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findByEstado(boolean estado) {
        List<Alerta> estadoList = alertaRepository.findByEstado(estado);
        List<AlertaDTO> estadoDTOList = MapperUtils.DtoListFromEntityList(estadoList, AlertaDTO.class);
        return Optional.ofNullable(estadoDTOList);
    }

    @Override
    @Transactional
    public Optional<AlertaDTO> create(AlertaDTO alertaDTO) {
        return Optional.ofNullable(getSavedActivoDTO(alertaDTO));
    }

    @Override
    @Transactional
    public Optional<AlertaDTO> update(AlertaDTO alertaDTO, Long id) {
        if (alertaRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedActivoDTO(alertaDTO));
    }

    private AlertaDTO getSavedActivoDTO(AlertaDTO alertaDTO) {
        Alerta alerta = MapperUtils.EntityFromDto(alertaDTO, Alerta.class);
        Alerta alertaCreated = alertaRepository.save(alerta);
        return MapperUtils.DtoFromEntity(alertaCreated, AlertaDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alertaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        alertaRepository.deleteAll();
    }
}
