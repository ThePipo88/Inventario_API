package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.*;
import org.una.inventario.services.IUsuarioService;
import org.una.inventario.services.IValuacionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/valuaciones")
@Api(tags = {"Valuaciones"})
public class ValuacionesController {

    @Autowired
    private IValuacionService valuacionService;

    @ApiOperation(value = "Obtiene una lista de todos las valuaciones", response = ValuacionDTO.class, responseContainer = "List", tags = "Valuaciones")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ValuacionDTO>> result = valuacionService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una valuacion a partir de su id", response = ValuacionDTO.class,  responseContainer = "List", tags = "Valuacion")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ValuacionDTO> activoFound = valuacionService.findById(id);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de valuaciones a partir de su id", response = ValuacionDTO.class, responseContainer = "List", tags = "Valuaciones")
    @GetMapping("/activo/{id}")
    public ResponseEntity<?> findByActivo(@PathVariable(value = "id") Long id) {
        Optional<List<ValuacionDTO>> activoFound = valuacionService.findByActivo(id);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de valuaciones a partir de su id", response = ValuacionDTO.class, responseContainer = "List", tags = "Valuaciones")
    @GetMapping("/inventario/{id}")
    public ResponseEntity<?> findByInventario(@PathVariable(value = "id") Long id) {
        Optional<List<ValuacionDTO>> activoFound = valuacionService.findByInventario(id);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @GetMapping("/byIdAndFecha/{endDate}")
    @ApiOperation(value = "Obtiene una lista de valuaciones de acuerdo al usuario y fecha de creacion", response = ValuacionDTO.class, responseContainer = "List", tags = "Valuaciones")
    public ResponseEntity<?> findByFechaCreacion(@PathVariable(value = "startDate") Date startDate) {
        Optional<List<ValuacionDTO>> result = valuacionService.findByFechaCreacion(startDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea una valuacion", response = ValuacionDTO.class, tags = "Valuacion")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ValuacionDTO valuacionDTO) {
        Optional<ValuacionDTO> usuarioCreated = valuacionService.create(valuacionDTO);
        return new ResponseEntity<>(usuarioCreated, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Se modifica una valuacion a partir de su id", response = ValuacionDTO.class, tags = "Valuacion")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ValuacionDTO valuacionModified) {
        Optional<ValuacionDTO> usuarioUpdated = valuacionService.update(valuacionModified, id);
        return new ResponseEntity<>(usuarioUpdated, HttpStatus.OK);
    }


    @ApiOperation(value = "Se elimina una valuacion a partir de su id", response = ValuacionDTO.class, tags = "Valuacion")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        valuacionService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todos las valuaciones", response = ValuacionDTO.class, tags = "valuacion")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        valuacionService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
