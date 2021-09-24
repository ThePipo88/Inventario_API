package org.una.inventario.dto;

import lombok.*;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Rol;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private String cedula;
<<<<<<< HEAD
=======
    private String passwordEncriptado;
>>>>>>> main
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private DepartamentoDTO departamento;
    private RolDTO rol;
    private boolean esJefe;
<<<<<<< HEAD
    private String passwordEncriptado;

=======
>>>>>>> main

}
