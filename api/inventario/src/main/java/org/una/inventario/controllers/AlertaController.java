package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.services.IActivoService;
import org.una.inventario.services.IAlertaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alerta")
@Api(tags = {"Alertas"})
public class AlertaController {

    @Autowired
    private IAlertaService alertaService;

    @ApiOperation(value = "Obtiene una lista de todos las alertas", response = AlertaDTO.class, responseContainer = "List", tags = "Alertas")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<AlertaDTO>> result = alertaService.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una alerta a partir de su id", response = AlertaDTO.class,  responseContainer = "List", tags = "Alerta")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<AlertaDTO> activoFound = alertaService.findById(id);
            return new ResponseEntity<>(activoFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una alerta a partir de su id", response = AlertaDTO.class, tags = "Activo")
    @GetMapping("/activo/{id}")
    public ResponseEntity<?> findByActivo(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<AlertaDTO>> activoFound = alertaService.findByActivo(id);
            return new ResponseEntity<>(activoFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene un activo a partir de su tipo", response = AlertaDTO.class, tags = "Alerta")
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByTipo(@PathVariable(value = "term") String term) {
        try {
            Optional<List<AlertaDTO>> result = alertaService.findByTipo(term);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una lista de alertas a partir de su estado", response = AlertaDTO.class,responseContainer = "List", tags = "Alertas")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        try {
            Optional<List<AlertaDTO>> result = alertaService.findByEstado(estado);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea una alerta", response = AlertaDTO.class, tags = "Activo")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody AlertaDTO alertaDTO) {
        try {
            Optional<AlertaDTO> alertaCreated = alertaService.create(alertaDTO);
            return new ResponseEntity<>(alertaCreated, HttpStatus.CREATED);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Se modifica una alerta a partir de su id", response = AlertaDTO.class, tags = "Alerta")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AlertaDTO alertaDTO) {
        try {
            Optional<AlertaDTO> alertaUpdated = alertaService.update(alertaDTO, id);
            return new ResponseEntity<>(alertaUpdated, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Se elimina una alerta a partir de su id", response = AlertaDTO.class, tags = "Alerta")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            alertaService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Se eliminan todas las alertas", response = AlertaDTO.class, tags = "Alerta")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            alertaService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
