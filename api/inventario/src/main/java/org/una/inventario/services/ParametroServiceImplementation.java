package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.dto.ParametroDTO;
import org.una.inventario.entities.Marca;
import org.una.inventario.entities.Parametro;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IMarcaRepository;
import org.una.inventario.repositories.IParametroRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroServiceImplementation implements IParametroService{

    @Autowired
    private IParametroRepository parametroRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroDTO>> findAll() {
        List<ParametroDTO> parametroDTOList = MapperUtils.DtoListFromEntityList(parametroRepository.findAll(), ParametroDTO.class);
        return Optional.ofNullable(parametroDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroDTO> findById(Long id) {
        Optional<Parametro> parametro = parametroRepository.findById(id);
        if (parametro.isEmpty()) throw new NotFoundInformationException();

        ParametroDTO parametroDTO = MapperUtils.DtoFromEntity(parametro.get(), ParametroDTO.class);
        return Optional.ofNullable(parametroDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroDTO>> findByValor(String valor) {
        List<Parametro> parametroList = parametroRepository.findByValor(valor);
        List<ParametroDTO> parametroDTOList = MapperUtils.DtoListFromEntityList(parametroList, ParametroDTO.class);
        return Optional.ofNullable(parametroDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroDTO>> findByNombre(String nombre) {
        List<Parametro> parametroList = parametroRepository.findByNombre(nombre);
        List<ParametroDTO> parametroDTOList = MapperUtils.DtoListFromEntityList(parametroList, ParametroDTO.class);
        return Optional.ofNullable(parametroDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroDTO>> findByEstado(Boolean estado) {
        List<Parametro> parametroList = parametroRepository.findByEstado(estado);
        List<ParametroDTO> parametroDTOList = MapperUtils.DtoListFromEntityList(parametroList, ParametroDTO.class);
        return Optional.ofNullable(parametroDTOList);
    }

    @Override
    @Transactional
    public Optional<ParametroDTO> create(ParametroDTO parametroDTO) {
        return Optional.ofNullable(getSavedParametroDTO(parametroDTO));
    }

    @Override
    @Transactional
    public Optional<ParametroDTO> update(ParametroDTO parametroDTO, Long id) {
        if (parametroRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedParametroDTO(parametroDTO));
    }

    private ParametroDTO getSavedParametroDTO(ParametroDTO parametroDTO) {
        Parametro parametro = MapperUtils.EntityFromDto(parametroDTO, Parametro.class);
        Parametro parametroCreated = parametroRepository.save(parametro);
        return MapperUtils.DtoFromEntity(parametroCreated, ParametroDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        parametroRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        parametroRepository.deleteAll();
    }
}
