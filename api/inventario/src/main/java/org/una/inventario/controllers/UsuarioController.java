package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.*;
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
    //@PreAuthorize("hasRole('USUARIO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<UsuarioDTO>> result = usuarioService.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una usuario a partir de su id", response = UsuarioDTO.class, tags = "Usuario")
    @GetMapping("/byId/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<UsuarioDTO> usuarioFound = usuarioService.findById(id);
            return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Obtiene una lista de usuarios a partir de su departamento",responseContainer = "List", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/byDepartamentoId/{id}")
    //@PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<UsuarioDTO>> result = usuarioService.findByDepartamentoId(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una usuario jefe a partir de su id", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/{Jid}")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<?> findJefeByDepartamento(@PathVariable(value = "Jid") Long Jid) {
        try {
            Optional<UsuarioDTO> usuarioFound = usuarioService.findJefeByDepartamento(Jid);
            return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene un usuario a partir de su cedula", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/byCedula/{cedula}")
    public ResponseEntity<?> findByCedula(@PathVariable(value = "cedula") String cedula) {
        try {
            Optional<UsuarioDTO> usuarioFound = usuarioService.findByCedula(cedula);
            return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una usuario a partir de su cedula", response = UsuarioDTO.class, tags = "Usuarios")
    @GetMapping("/cedula/{term}")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<UsuarioDTO>> result = usuarioService.findByCedulaAproximate(term);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una usuario a partir de su nombre", response = UsuarioDTO.class, tags = "Usuario")
    @GetMapping("/nombre/{term}")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<UsuarioDTO>> result = usuarioService.findByNombreCompletoAproximateIgnoreCase(term);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una usuario comparando su nombre", response = UsuarioDTO.class, tags = "Usuario")
    @GetMapping("/nombreC/{term}")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<?> findNombreCompletoWithLikeSQL(@PathVariable(value = "term") String term) {
        try {
            Optional<UsuarioDTO> result = usuarioService.findNombreCompletoWithLikeSQL(term);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un usuario", response = UsuarioDTO.class, tags = "Usuario")
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Optional<UsuarioDTO> usuarioCreated = usuarioService.create(usuarioDTO);
            return new ResponseEntity<>(usuarioCreated, HttpStatus.CREATED);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Se modifica un usuario a partir de su id", response = UsuarioDTO.class, tags = "Usuario")
    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioDTO usuarioModified) {
        try {
            Optional<UsuarioDTO> usuarioUpdated = usuarioService.update(usuarioModified, id);
            return new ResponseEntity<>(usuarioUpdated, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Se elimina un usuario a partir de su id", response = UsuarioDTO.class, tags = "Usuario")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CONTADOR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            usuarioService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Se eliminan todos los departamentos", response = UsuarioDTO.class, tags = "Usuarios")
    @DeleteMapping("/")
    @PreAuthorize("hasRole('CONTADOR')")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            usuarioService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
