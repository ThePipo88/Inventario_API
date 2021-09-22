package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.*;
import org.una.inventario.services.IInventarioService;
import org.una.inventario.services.IMarcaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas")
@Api(tags = {"Marcas"})
public class MarcasController {

    @Autowired
    private IMarcaService marcaService;

    @ApiOperation(value = "Obtiene una lista de todos las marcas", response = MarcaDTO.class, responseContainer = "List", tags = "Marcas")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<MarcaDTO>> result = marcaService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una marca a partir de su id", response = MarcaDTO.class,  responseContainer = "List", tags = "Marca")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<MarcaDTO> marcaFound = marcaService.findById(id);
        return new ResponseEntity<>(marcaFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una marca a partir de su nombre", response = MarcaDTO.class, tags = "Marca")
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        Optional<List<MarcaDTO>> result = marcaService.findByNombre(term);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una marca a partir de su estado", response = MarcaDTO.class, responseContainer = "List", tags = "Marcas")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        Optional<List<MarcaDTO>> result = marcaService.findByEstado(estado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea una marca", response = MarcaDTO.class, tags = "Marca")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody MarcaDTO marcaDTO) {
        Optional<MarcaDTO> marcaCreated = marcaService.create(marcaDTO);
        return new ResponseEntity<>(marcaCreated, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Se modifica una marca a partir de su id", response = MarcaDTO.class, tags = "Marca")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody MarcaDTO marcaDTO) {
        Optional<MarcaDTO> marcaUpdated = marcaService.update(marcaDTO, id);
        return new ResponseEntity<>(marcaUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Se elimina un inventario a partir de su id", response = MarcaDTO.class, tags = "Marca")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        marcaService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todos las marcas", response = MarcaDTO.class, tags = "Marca")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        marcaService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
  }

