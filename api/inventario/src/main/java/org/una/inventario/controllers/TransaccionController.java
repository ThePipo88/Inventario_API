package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.TransaccionDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Transaccion;
import org.una.inventario.services.ITransaccionService;
import org.una.inventario.services.IUsuarioService;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transferencias")
@Api(tags = {"Transferencias"})
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    @ApiOperation(value = "Obtiene una transaccion a partir de su id", response = TransaccionDTO.class, tags = "Transacciones")
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<TransaccionDTO> usuarioFound = transaccionService.findById(id);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
    }

    @GetMapping("/byIdAndFecha/{id}/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de transacciones de acuerdo al usuario y fecha de creacion", response = TransaccionDTO.class, responseContainer = "TransaccionDto", tags = "Transacciones")
    public ResponseEntity<?> findByUsuarioIdAndFechaCreacionBetween(@PathVariable(value = "id") Long id, @PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate") Date endDate) {
        Optional<List<TransaccionDTO>> result = transaccionService.findByUsuarioIdAndFechaCreacionBetween(id,startDate,endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/byRollAndFecha/{id}/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de transacciones de acuerdo al roll y fecha de creacion", response = TransaccionDTO.class, responseContainer = "TransaccionDto", tags = "Transacciones")
    public ResponseEntity<?> findByRolIdAndFechaCreacionBetween(@PathVariable(value = "id") Long id, @PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate") Date endDate) {
        Optional<List<TransaccionDTO>> result = transaccionService.findByRolIdAndFechaCreacionBetween(id,startDate,endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/byObjetoAndFecha/{id}/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de transacciones de acuerdo al objeto y fecha de creacion", response = TransaccionDTO.class, responseContainer = "TransaccionDto", tags = "Transacciones")
    public ResponseEntity<?> findByObjetoAndFechaCreacionBetween(@PathVariable(value = "id") String id, @PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate") Date endDate) {
        Optional<List<TransaccionDTO>> result = transaccionService.findByObjetoAndFechaCreacionBetween(id,startDate,endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/byFechaCreacion/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de transacciones de acuerdo a su fecha de creacion", response = TransaccionDTO.class, responseContainer = "TransaccionDto", tags = "Transacciones")
    public ResponseEntity<?> findByFechaCreacionBetween(@PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate") Date endDate) {
        Optional<List<TransaccionDTO>> result = transaccionService.findByFechaCreacionBetween(startDate,endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea una transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TransaccionDTO transaccionDTO) {
        TransaccionDTO transaccionCreated = transaccionService.create(transaccionDTO);
        TransaccionDTO transaccionDto = MapperUtils.DtoFromEntity(transaccionCreated, TransaccionDTO.class);
        return new ResponseEntity<>(transaccionDto, HttpStatus.CREATED);
    }
}