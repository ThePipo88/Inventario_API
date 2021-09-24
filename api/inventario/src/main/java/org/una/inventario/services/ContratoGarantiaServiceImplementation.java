package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.dto.ContratoGarantiaDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Categoria;
import org.una.inventario.entities.ContratoGarantia;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.ICategoriaRepository;
import org.una.inventario.repositories.IContratoGarantiaRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoGarantiaServiceImplementation implements IContratoGarantiaService{

    @Autowired
    private IContratoGarantiaRepository contratoGarantiaRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<List<ContratoGarantiaDTO>> findAll() {
        List<ContratoGarantiaDTO> contratoGarantiaDTOList = MapperUtils.DtoListFromEntityList(contratoGarantiaRepository.findAll(), ContratoGarantiaDTO.class);
        return Optional.ofNullable(contratoGarantiaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContratoGarantiaDTO> findById(Long id) {
        Optional<ContratoGarantia> contratoGarantia = contratoGarantiaRepository.findById(id);
        if (contratoGarantia.isEmpty()) throw new NotFoundInformationException();

        ContratoGarantiaDTO contratoGarantiaDTO = MapperUtils.DtoFromEntity(contratoGarantia.get(), ContratoGarantiaDTO.class);
        return Optional.ofNullable(contratoGarantiaDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByActivo(Long id) {
        List<Activo> activoList = contratoGarantiaRepository.findByActivo(id);
        List<ActivoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoList, ActivoDTO.class);
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ContratoGarantiaDTO>> findByEstado(boolean estado) {
        List<ContratoGarantia> contratoGarantiaDTOList = contratoGarantiaRepository.findByEstado(estado);
        List<ContratoGarantiaDTO> acontratoGarantiaDTOList = MapperUtils.DtoListFromEntityList(contratoGarantiaDTOList, ContratoGarantiaDTO.class);
        return Optional.ofNullable(acontratoGarantiaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ContratoGarantiaDTO>> findByCosto(double costo) {
        List<ContratoGarantia> contratoGarantiaDTOList = contratoGarantiaRepository.findByCosto(costo);
        List<ContratoGarantiaDTO> acontratoGarantiaDTOList = MapperUtils.DtoListFromEntityList(contratoGarantiaDTOList, ContratoGarantiaDTO.class);
        return Optional.ofNullable(acontratoGarantiaDTOList);
    }

    @Override
    @Transactional
    public Optional<ContratoGarantiaDTO> create(ContratoGarantiaDTO contratoGarantiaDTO) {
        return Optional.ofNullable(getSavedContratoGarantiaDTO(contratoGarantiaDTO));
    }

    @Override
    @Transactional
    public Optional<ContratoGarantiaDTO> update(ContratoGarantiaDTO contratoGarantiaDTO, Long id) {
        if (contratoGarantiaRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedContratoGarantiaDTO(contratoGarantiaDTO));
    }

    private ContratoGarantiaDTO getSavedContratoGarantiaDTO(ContratoGarantiaDTO contratoGarantiaDTO) {
        ContratoGarantia contratoGarantia = MapperUtils.EntityFromDto(contratoGarantiaDTO, ContratoGarantia.class);
        ContratoGarantia contratoCreated = contratoGarantiaRepository.save(contratoGarantia);
        return MapperUtils.DtoFromEntity(contratoCreated, ContratoGarantiaDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        contratoGarantiaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        contratoGarantiaRepository.deleteAll();
    }
}
