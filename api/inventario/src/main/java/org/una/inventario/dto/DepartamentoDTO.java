package org.una.inventario.dto;

import lombok.*;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Usuario;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DepartamentoDTO {

    private Long id;
    private String nombre;
    private boolean estado;
   // private List<Usuario> usuarios;
    private Date fechaRegistro;
    private Date fechaModificacion;
}