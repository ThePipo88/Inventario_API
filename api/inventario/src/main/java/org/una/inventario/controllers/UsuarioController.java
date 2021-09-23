package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.AuthenticationRequest;
import org.una.inventario.dto.AuthenticationResponse;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Departamento;
import org.una.inventario.exceptions.InvalidCredentialsException;
import org.una.inventario.exceptions.MissingInputsException;
import org.una.inventario.services.IUsuarioService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"})
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<UsuarioDTO>> result = usuarioService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una usuario a partir de su id", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<UsuarioDTO> usuarioFound = usuarioService.findById(id);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una usuario jefe a partir de su id", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/{Jid}")
    public ResponseEntity<?> findJefeByDepartamento(@PathVariable(value = "Jid") Long Jid) {
        Optional<UsuarioDTO> usuarioFound = usuarioService.findJefeByDepartamento(Jid);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un usuario a partir de su cedula", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/byCedula/{cedula}")
    public ResponseEntity<?> findByCedula(@PathVariable(value = "cedula") String cedula) {
        Optional<UsuarioDTO> usuarioFound = usuarioService.findByCedula(cedula);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { throw new MissingInputsException();  }
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        AuthenticationResponse token = usuarioService.login(authenticationRequest);
        if (token.getJwt() != null) {
            authenticationResponse.setJwt(token.getJwt());
            //TODO: Complete this   authenticationResponse.setUsuario(usuario);
            //TODO: Complete this    authenticationResponse.setPermisos(permisosOtorgados);
            return new ResponseEntity(authenticationResponse, HttpStatus.OK);
        } else {
            throw new InvalidCredentialsException();
        }
    }

    @ApiOperation(value = "Obtiene una usuario a partir de su cedula", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/cedula/{term}")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        Optional<List<UsuarioDTO>> result = usuarioService.findByCedulaAproximate(term);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @ApiOperation(value = "Obtiene una lista de usuarios a partir de su departamento",responseContainer = "List", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/{Did}")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "Did") Long Did) {
        Optional<List<DepartamentoDTO>> result = usuarioService.findByDepartamentoId(Did);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una usuario a partir de su nombre", response = UsuarioDTO.class, tags = "Usuario")
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        Optional<List<UsuarioDTO>> result = usuarioService.findByNombreCompletoAproximateIgnoreCase(term);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Duda en este metodo
    @ApiOperation(value = "Obtiene una usuario comparando su nombre", response = UsuarioDTO.class, tags = "Usuario")
    @GetMapping("/nombreC/{term}")
    public ResponseEntity<?> findNombreCompletoWithLikeSQL(@PathVariable(value = "term") String term) {
        Optional<UsuarioDTO> result = usuarioService.findNombreCompletoWithLikeSQL(term);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un usuario", response = UsuarioDTO.class, tags = "Usuario")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO) {
        Optional<UsuarioDTO> usuarioCreated = usuarioService.create(usuarioDTO);
        return new ResponseEntity<>(usuarioCreated, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Se modifica un usuario a partir de su id", response = UsuarioDTO.class, tags = "Usuario")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioDTO usuarioModified) {
            Optional<UsuarioDTO> usuarioUpdated = usuarioService.update(usuarioModified, id);
            return new ResponseEntity<>(usuarioUpdated, HttpStatus.OK);
    }


    @ApiOperation(value = "Se elimina un usuario a partir de su id", response = UsuarioDTO.class, tags = "Usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
            usuarioService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todos los departamentos", response = UsuarioDTO.class, tags = "Usuarios")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
         usuarioService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
