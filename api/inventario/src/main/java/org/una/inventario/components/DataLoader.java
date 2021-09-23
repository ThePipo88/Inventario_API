package org.una.inventario.components;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.services.IDepartamentoService;
import org.una.inventario.services.IRolService;
import org.una.inventario.services.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.lang.String;

@Component
public class DataLoader implements ApplicationRunner {

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private IDepartamentoService departamentoService;


    @Override
    public void run(ApplicationArguments args) {
        if (usuarioService.findByCedulaAproximate(cedula).isEmpty()) {
            Optional<DepartamentoDTO> contabilidadDepartamento = departamentoService.create(DepartamentoDTO.builder().nombre("Contabilidad").build());
            Optional<DepartamentoDTO> cajasDepartamento = departamentoService.create(DepartamentoDTO.builder().nombre("Cajas").build());
            Optional<DepartamentoDTO> informaticaDepartamento = departamentoService.create(DepartamentoDTO.builder().nombre("Informatica").build());

            Optional<RolDTO> colaboradorRol = Optional.ofNullable(rolService.create(RolDTO.builder().nombre("Colaborador").build()));
            Optional<RolDTO> auditorRol = Optional.ofNullable(rolService.create(RolDTO.builder().nombre("Auditor").build()));
            Optional<RolDTO> contadorRol = Optional.ofNullable(rolService.create(RolDTO.builder().nombre("Contador").build()));
            Optional<RolDTO> usuarioRol = Optional.ofNullable(rolService.create(RolDTO.builder().nombre("Usuario").build()));
            Optional<RolDTO> administradorRol = Optional.ofNullable(rolService.create(RolDTO.builder().nombre("Administrador").build()));

            UsuarioDTO cajeroUsuario = UsuarioDTO.builder()
                    .cedula("0123456789")
                    .nombreCompleto("Usuario Prueba Cajero")
                    .passwordEncriptado("Una2021")
                    .departamento(cajasDepartamento.orElseThrow())
                    .rol(usuarioRol.orElseThrow()).build();
            usuarioService.create(cajeroUsuario);

            UsuarioDTO contadorUsuario = UsuarioDTO.builder()
                    .cedula("9876543210")
                    .nombreCompleto("Usuario Prueba Contador")
                    .esJefe(true)
                    .passwordEncriptado("Una2021")
                    .departamento(contabilidadDepartamento.orElseThrow())
                    .rol(contadorRol.orElseThrow()).build();
            usuarioService.create(contadorUsuario);

            UsuarioDTO administradorUsuario = UsuarioDTO.builder()
                    .cedula(cedula)
                    .nombreCompleto("Usuario Administrador")
                    .passwordEncriptado(password)
                    .departamento(informaticaDepartamento.orElseThrow())
                    .rol(administradorRol.orElseThrow()).build();
            usuarioService.create(administradorUsuario);

            System.out.println("Se agrega el usuario inicial a la aplicación");
        }else {
            System.out.println("Se encontro el usuario administrador, continuando...");

            Boolean creadte = false;

            Optional<List<UsuarioDTO>> usuario = usuarioService.findAll();

            for(int i = 0; i < usuario.get().size(); i++){
                if(!usuario.get().isEmpty()){
                    if(usuario.get().get(i).getCedula().equals("admin")){
                        creadte = true;
                    }
                }

            }

            if(!creadte)
            {
                Optional<DepartamentoDTO> informaticaDepartamento = departamentoService.create(DepartamentoDTO.builder().nombre("Informatica").build());
                Optional<RolDTO> administradorRol = Optional.ofNullable(rolService.create(RolDTO.builder().nombre("Administrador").build()));

                UsuarioDTO administrador = UsuarioDTO.builder()
                        .cedula("admin")
                        .nombreCompleto("Usuario Administrador")
                        .passwordEncriptado("Una2021")
                        .rol(administradorRol.orElseThrow()).departamento(informaticaDepartamento.orElseThrow())
                        .build();
                usuarioService.create(administrador);
            }
            else{
                System.out.println("Usuario existente");
            }
        }
    }

}

