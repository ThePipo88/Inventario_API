package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.InventarioDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.dto.ValuacionDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Inventario;
import org.una.inventario.entities.Usuario;
import org.una.inventario.entities.Valuacion;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IValuacionRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ValuacionServiceImplementation implements IValuacionService{

    @Autowired
    private IValuacionRepository valuacionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionDTO>> findAll() {
        List<ValuacionDTO> valuacionDTOList = MapperUtils.DtoListFromEntityList(valuacionRepository.findAll(), ValuacionDTO.class);
        return Optional.ofNullable(valuacionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ValuacionDTO> findById(Long id) {
        Optional<Valuacion> valuacion = valuacionRepository.findById(id);
        if (valuacion.isEmpty()) throw new NotFoundInformationException();

        ValuacionDTO valuacionDTO = MapperUtils.DtoFromEntity(valuacion.get(), ValuacionDTO.class);
        return Optional.ofNullable(valuacionDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionDTO>> findByActivo(Long id) {
        List<Valuacion> activoList = valuacionRepository.findByActivo(id);
        List<ValuacionDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoList, ValuacionDTO.class);
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionDTO>> findByInventario(Long id) {
        List<Valuacion> inventarioList = valuacionRepository.findByInventario(id);
        List<ValuacionDTO> inventarioDTOList = MapperUtils.DtoListFromEntityList(inventarioList, ValuacionDTO.class);
        return Optional.ofNullable(inventarioDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionDTO>> findByFechaCreacion(Date startDate) {
        List<Valuacion> valuacionList = valuacionRepository.findByFechaCreacion(startDate);
        List<ValuacionDTO> valuacionDTOList = MapperUtils.DtoListFromEntityList(valuacionList, ValuacionDTO.class);
        return Optional.ofNullable(valuacionDTOList);
    }

    @Override
    @Transactional
    public Optional<ValuacionDTO> create(ValuacionDTO valuacionDTO) {
        return Optional.ofNullable(getSavedValuacionDTO(valuacionDTO));
    }

    @Override
    @Transactional
    public Optional<ValuacionDTO> update(ValuacionDTO valuacionDTO, Long id) {
        if (valuacionRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedValuacionDTO(valuacionDTO));
    }

    private ValuacionDTO getSavedValuacionDTO(ValuacionDTO valuacionDTO) {
        Valuacion valuacion = MapperUtils.EntityFromDto(valuacionDTO, Valuacion.class);
        Valuacion valuacionCreated = valuacionRepository.save(valuacion);
        return MapperUtils.DtoFromEntity(valuacionCreated, ValuacionDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        valuacionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        valuacionRepository.deleteAll();
    }
}
