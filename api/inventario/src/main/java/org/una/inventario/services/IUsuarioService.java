package org.una.inventario.services;

import org.springframework.data.repository.query.Param;
import org.una.inventario.dto.AuthenticationRequest;
import org.una.inventario.dto.AuthenticationResponse;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Usuario;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.utils.MapperUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public Optional<List<UsuarioDTO>> findAll();

    public Optional<UsuarioDTO> findById(Long id);

    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula);

    public Optional<UsuarioDTO> findByCedula(String cedula);

    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public Optional<UsuarioDTO> findNombreCompletoWithLikeSQL(@Param("nombreCompleto")String nombreCompleto);

    public Optional<List<DepartamentoDTO>> findByDepartamentoId(Long id);

    public Optional<UsuarioDTO> create(UsuarioDTO usuarioDTO);

    public Optional<UsuarioDTO> findJefeByDepartamento(Long id);

    public Optional<UsuarioDTO> update(UsuarioDTO usuarioDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
