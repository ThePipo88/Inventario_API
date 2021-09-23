package org.una.inventario.dto;

import lombok.*;
import org.una.inventario.entities.Usuario;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TransaccionDTO {

    private Long id;
    private String objeto;
    private Usuario usuarioId;
    private String accion;
    private Date fechaRegistro;
    private Date fechaFinalizado;
}
