package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.AuthenticationRequest;
import org.una.inventario.dto.AuthenticationResponse;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Usuario;
import org.una.inventario.exceptions.InvalidCredentialsException;
import org.una.inventario.jwt.JwtProvider;
import org.una.inventario.utils.MapperUtils;

import java.util.Optional;

@Repository
public class AutenticacionServiceImplementation implements IAutenticacionService{

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

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
}
