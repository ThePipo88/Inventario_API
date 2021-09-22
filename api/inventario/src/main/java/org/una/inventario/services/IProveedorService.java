package org.una.inventario.services;

import org.una.inventario.dto.ParametroDTO;
import org.una.inventario.dto.ProveedorDTO;
import org.una.inventario.entities.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {

    public Optional<List<ProveedorDTO>> findAll();

    public Optional<ProveedorDTO> findById(Long id);

    public Optional<List<ProveedorDTO>> findByNombre(String nombre);

    public Optional<ProveedorDTO> findByTelefono(String telefono);

    public Optional<ProveedorDTO> findByCorreo(String correo);

    public Optional<List<ProveedorDTO>> findByEstado(Boolean estado);

    public Optional<ProveedorDTO> create(ProveedorDTO proveedorDTO);

    public Optional<ProveedorDTO> update(ProveedorDTO proveedorDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

}
