package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.dto.ParametroDTO;
import org.una.inventario.services.IMarcaService;
import org.una.inventario.services.IParametroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parametros")
@Api(tags = {"Parametros"})
public class ParametrosController {

    @Autowired
    private IParametroService parametroService;

    @ApiOperation(value = "Obtiene una lista de todos las parametros", response = ParametroDTO.class, responseContainer = "List", tags = "Parametros")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ParametroDTO>> result = parametroService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un parametro a partir de su id", response = ParametroDTO.class,  responseContainer = "List", tags = "Parametro")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ParametroDTO> parametroFound = parametroService.findById(id);
        return new ResponseEntity<>(parametroFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un parametro a partir de su id", response = ParametroDTO.class,  responseContainer = "List", tags = "Parametros")
    @GetMapping("/{valor}")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor) {
        Optional<List<ParametroDTO>> valorFound = parametroService.findByValor(valor);
        return new ResponseEntity<>(valorFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un parametro a partir de su nombre", response = ParametroDTO.class, tags = "Parametros")
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        Optional<List<ParametroDTO>> result = parametroService.findByNombre(term);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un parametro a partir de su estado", response = ParametroDTO.class, responseContainer = "List", tags = "Parametros")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        Optional<List<ParametroDTO>> result = parametroService.findByEstado(estado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un parametro", response = ParametroDTO.class, tags = "Parametro")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ParametroDTO parametroDTO) {
        Optional<ParametroDTO> parametroCreated = parametroService.create(parametroDTO);
        return new ResponseEntity<>(parametroCreated, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Se modifica un parametro a partir de su id", response = ParametroDTO.class, tags = "Parametro")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametroDTO parametroDTO) {
        Optional<ParametroDTO> parametroUpdated = parametroService.update(parametroDTO, id);
        return new ResponseEntity<>(parametroUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Se elimina un parametro a partir de su id", response = ParametroDTO.class, tags = "Parametro")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        parametroService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todos las parametros", response = ParametroDTO.class, tags = "Parametros")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        parametroService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
