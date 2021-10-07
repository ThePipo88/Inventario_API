package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.services.IActivoAsignadoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activosAignados")
@Api(tags = {"ActivosAsignados"})
public class ActivosAsignadosController {

    @Autowired
    private IActivoAsignadoService activoAsignadoService;

    @ApiOperation(value = "Obtiene una lista de todos los activos asignados", response = ActivoAsignadoDTO.class, responseContainer = "List", tags = "ActivosAsignados")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ActivoAsignadoDTO>> result = activoAsignadoService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un activo asignado a partir de su id", response = ActivoAsignadoDTO.class, responseContainer = "List", tags = "ActivosAsignados")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ActivoAsignadoDTO> departamentoFound = activoAsignadoService.findById(id);
        return new ResponseEntity<>(departamentoFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un usuario asignado a partir de su id", response = ActivoAsignadoDTO.class, responseContainer = "List", tags = "ActivosAsignados")
    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> findByUsuario(@PathVariable(value = "id") Long id) {
        Optional<List<ActivoAsignadoDTO>> usuarioFound = activoAsignadoService.findByUsuario(id);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de activos asignados a partir de su estado", response = ActivoAsignadoDTO.class, responseContainer = "List", tags = "ActivosAsignados")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        Optional<List<ActivoAsignadoDTO>> result = activoAsignadoService.findByEstado(estado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un activo asignado", response = ActivoAsignadoDTO.class, tags = "ActivoAsignado")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ActivoAsignadoDTO activoAsignadoDTO) {
        Optional<ActivoAsignadoDTO> activoAsignadoCreated = activoAsignadoService.create(activoAsignadoDTO);
        return new ResponseEntity<>(activoAsignadoCreated, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Se modifica un activo asignado a partir de su id", response = DepartamentoDTO.class, tags = "ActivoAsignado")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ActivoAsignadoDTO activoAsignadoModified) {
        Optional<ActivoAsignadoDTO> activoAsignadoUpdated = activoAsignadoService.update(activoAsignadoModified, id);
        return new ResponseEntity<>(activoAsignadoUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Se elimina un activo asignado a partir de su id", response = DepartamentoDTO.class, tags = "ActivoAsignado")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        activoAsignadoService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todos los activos asignados", response = DepartamentoDTO.class, tags = "ActivoAsignado")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        activoAsignadoService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
