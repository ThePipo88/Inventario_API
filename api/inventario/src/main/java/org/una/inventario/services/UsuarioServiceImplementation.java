package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.*;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Usuario;
import org.una.inventario.exceptions.InvalidCredentialsException;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.jwt.JwtProvider;
import org.una.inventario.repositories.IUsuarioRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImplementation implements IUsuarioService, UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        List<Usuario> usuarioList = usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto);
        List<UsuarioDTO> usuarioDTOList = MapperUtils.DtoListFromEntityList(usuarioList, UsuarioDTO.class);
        return Optional.ofNullable(usuarioDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findNombreCompletoWithLikeSQL(@Param("nombreCompleto")String nombreCompleto) {
        Usuario usuario = usuarioRepository.findNombreCompletoWithLikeSQL(nombreCompleto);
        return Optional.ofNullable(MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByDepartamentoId(Long id) {
        List<Departamento> departamentoList = usuarioRepository.findByDepartamentoId(id);
        List<DepartamentoDTO> departamentoDTOList = MapperUtils.DtoListFromEntityList(departamentoList, DepartamentoDTO.class);
        return Optional.ofNullable(departamentoDTOList);
    }

    private UsuarioDTO getSavedUsuarioDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario = MapperUtils.EntityFromDto(usuarioDTO, Usuario.class);
        Usuario usuarioCreated = usuarioRepository.save(usuario);
        return MapperUtils.DtoFromEntity(usuarioCreated, UsuarioDTO.class);
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> create(UsuarioDTO usuarioDTO) {
        String pEncriptado = encriptarPassword(usuarioDTO.getPasswordEncriptado());
        usuarioDTO.setPasswordEncriptado(pEncriptado);
        return Optional.ofNullable(getSavedUsuarioDTO(usuarioDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findJefeByDepartamento(Long id) {
        Usuario usuario = usuarioRepository.findJefeByDepartamento(id);
        return Optional.ofNullable(MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByCedula(String cedula) {
        Usuario usuario = usuarioRepository.findByCedula(cedula);
        return Optional.ofNullable(MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class));
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> update(UsuarioDTO usuarioDTO, Long id) {
        if (usuarioRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedUsuarioDTO(usuarioDTO));

    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

    /*
    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByCedula(authenticationRequest.getCedula()));

        if (usuario.isPresent() &&  bCryptPasswordEncoder.matches(authenticationRequest.getPassword(),usuario.get().getPasswordEncriptado())) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDTO.class);
            authenticationResponse.setUsuarioDTO(usuarioDto);
            authenticationResponse.setRolDTO(RolDTO.builder().nombre(usuarioDto.getRol().getNombre()).build());

            return authenticationResponse;
        } else {
            throw new InvalidCredentialsException();
        }
    }
   */
    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) throw new NotFoundInformationException();

        UsuarioDTO usuarioDTO = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDTO.class);
        return Optional.ofNullable(usuarioDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> usuarioDTOList = MapperUtils.DtoListFromEntityList(usuarioRepository.findAll(), UsuarioDTO.class);
        return Optional.ofNullable(usuarioDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula) {
        List<Usuario> usuarioList = usuarioRepository.findByCedulaContaining(cedula);
        List<UsuarioDTO> usuarioDTOList = MapperUtils.DtoListFromEntityList(usuarioList, UsuarioDTO.class);
        return Optional.ofNullable(usuarioDTOList);
    }

    private String encriptarPassword(String password) {
        if (!password.isBlank()) {
            return bCryptPasswordEncoder.encode(password);
        }else{
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = Optional.ofNullable(usuarioRepository.findByCedula(username));
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
            return new User(usuario.getCedula(), usuario.getPasswordEncriptado(), roles);
        } else {
            throw new UsernameNotFoundException("Username not found, check your request");
        }
    }
}
