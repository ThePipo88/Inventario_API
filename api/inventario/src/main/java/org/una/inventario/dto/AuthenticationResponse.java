package org.una.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
<<<<<<< HEAD
=======
import org.una.inventario.dto.RolDTO;
import org.una.inventario.dto.UsuarioDTO;
>>>>>>> main

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse {

    private String jwt;
    private UsuarioDTO usuarioDTO;
    private RolDTO rolDTO;

}
<<<<<<< HEAD

=======
>>>>>>> main
