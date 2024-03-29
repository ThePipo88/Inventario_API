package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.*;
import org.una.inventario.services.IDepartamentoService;
import org.una.inventario.services.IInventarioService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventarios")
@Api(tags = {"Inventarios"})
public class InventariosController {

    @Autowired
    private IInventarioService inventarioService;

    @ApiOperation(value = "Obtiene una lista de todos los inventarios", response = InventarioDTO.class, responseContainer = "List", tags = "Inventarios")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<InventarioDTO>> result = inventarioService.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene un inventario a partir de su id", response = InventarioDTO.class,  responseContainer = "List", tags = "Inventario")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<InventarioDTO> inventarioFound = inventarioService.findById(id);
            return new ResponseEntity<>(inventarioFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene un inventario a partir de su estado", response = InventarioDTO.class,responseContainer = "List", tags = "Inventario")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        try {
            Optional<List<InventarioDTO>> result = inventarioService.findByEstado(estado);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byIdAndFecha/{endDate}")
    @ApiOperation(value = "Obtiene una lista de inventarios de acuerdo al usuario y fecha de creacion", response = TransaccionDTO.class, responseContainer = "InventariosDto", tags = "Inventarios")
    public ResponseEntity<?> findByFechaCreacion(@PathVariable(value = "startDate") Date startDate) {
        try {
            Optional<List<InventarioDTO>> result = inventarioService.findByFechaCreacion(startDate);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un inventario", response = InventarioDTO.class, tags = "Inventario")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody InventarioDTO inventarioDTO) {
        try {
            Optional<InventarioDTO> inventarioCreated = inventarioService.create(inventarioDTO);
            return new ResponseEntity<>(inventarioCreated, HttpStatus.CREATED);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Se modifica un inventario a partir de su id", response = InventarioDTO.class, tags = "Inventario")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody InventarioDTO inventarioDTO) {
        try {
            Optional<InventarioDTO> inventarioUpdated = inventarioService.update(inventarioDTO, id);
            return new ResponseEntity<>(inventarioUpdated, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Se elimina un inventario a partir de su id", response = InventarioDTO.class, tags = "Inventario")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            inventarioService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Se eliminan todos los inventarios", response = InventarioDTO.class, tags = "Inventario")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            inventarioService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

