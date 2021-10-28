package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Departamento;
import org.una.inventario.services.IDepartamentoService;
import org.una.inventario.services.IUsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
@Api(tags = {"Departamentos"})
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;
    @PreAuthorize("hasRole('AUDITOR')")

    @ApiOperation(value = "Obtiene una lista de todos los departamentos", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<DepartamentoDTO>> result = departamentoService.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")

    @ApiOperation(value = "Obtiene un departamento a partir de su id", response = DepartamentoDTO.class,  responseContainer = "List", tags = "Departamento")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<DepartamentoDTO> departamentoFound = departamentoService.findById(id);
            return new ResponseEntity<>(departamentoFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")

    @ApiOperation(value = "Obtiene un departamento a partir de su nombre", response = DepartamentoDTO.class, tags = "Departamento")
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombreContainingAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<DepartamentoDTO>> result = departamentoService.findByNombreContainingAproximateIgnoreCase(term);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('AUDITOR')")

    @ApiOperation(value = "Obtiene un departamento a partir de su estado", response = DepartamentoDTO.class,responseContainer = "List", tags = "Departamento")
    @GetMapping("/findByEstado/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        try {
            Optional<List<DepartamentoDTO>> result = departamentoService.findByEstado(estado);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un departamento", response = DepartamentoDTO.class, tags = "Departamento")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody DepartamentoDTO departamentoDTO) {
        try {
            Optional<DepartamentoDTO> departamentoCreated = departamentoService.create(departamentoDTO);
            return new ResponseEntity<>(departamentoCreated, HttpStatus.CREATED);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")

    @ApiOperation(value = "Se modifica un departamento a partir de su id", response = DepartamentoDTO.class, tags = "Departamento")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody DepartamentoDTO departamentoModified) {
        try {
            Optional<DepartamentoDTO> departamentoUpdated = departamentoService.update(departamentoModified, id);
            return new ResponseEntity<>(departamentoModified, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")

    @ApiOperation(value = "Se elimina un departamento a partir de su id", response = DepartamentoDTO.class, tags = "Departamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            departamentoService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")

    @ApiOperation(value = "Se eliminan todos los departamentos", response = DepartamentoDTO.class, tags = "Departamento")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            departamentoService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
