package org.una.inventario.services;

import org.una.inventario.dto.RolDTO;
import org.una.inventario.entities.Rol;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IRolService {

    public Optional<RolDTO> findById(Long id);

    public Optional<List<RolDTO>> findByEstado(boolean estado);

    public Optional<List<RolDTO>> findByFechaCreacionBetween(Date startDate, Date endDate);

    public RolDTO create(RolDTO rolDTO);

    public Optional<RolDTO> update(RolDTO rolDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

}
