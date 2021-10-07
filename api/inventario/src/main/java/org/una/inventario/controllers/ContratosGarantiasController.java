package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.*;
import org.una.inventario.services.ICategoriaService;
import org.una.inventario.services.IContratoGarantiaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contratoGarantia")
@Api(tags = {"Categorias"})
public class ContratosGarantiasController {

    @Autowired
    private IContratoGarantiaService contratoGarantiaService;

    @ApiOperation(value = "Obtiene una lista de todos los contratos de garantias", response = ActivoDTO.class, responseContainer = "List", tags = "ContratosGarantias")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ContratoGarantiaDTO>> result = contratoGarantiaService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un contrato de garantia a partir de su id", response = ActivoDTO.class,  responseContainer = "List", tags = "ContratoGarantia")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ContratoGarantiaDTO> contratoGarantiaFound = contratoGarantiaService.findById(id);
        return new ResponseEntity<>(contratoGarantiaFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un contrato a partir de un id de un activo", response = ActivoDTO.class, tags = "Activo")
    @GetMapping("/activo/{id}")
    public ResponseEntity<?> findByActivo(@PathVariable(value = "id") Long id) {
        Optional<List<ContratoGarantiaDTO>> activoFound = contratoGarantiaService.findByActivo(id);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de contratos de garantia a partir de su estado", response = ActivoDTO.class,responseContainer = "List", tags = "ContratosGarantias")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        Optional<List<ContratoGarantiaDTO>> result = contratoGarantiaService.findByEstado(estado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de costos partir de su costo", response = ActivoDTO.class,responseContainer = "List", tags = "ContratosGarantias")
    @GetMapping("/{costo}")
    public ResponseEntity<?> findByCosto(@PathVariable(value = "costo") double costo) {
        Optional<List<ContratoGarantiaDTO>> result = contratoGarantiaService.findByCosto(costo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea una contrato de garantia", response = ActivoAsignadoDTO.class, tags = "ContratoGarantia")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ContratoGarantiaDTO contratoGarantiaDTO) {
        Optional<ContratoGarantiaDTO> contratoCreated = contratoGarantiaService.create(contratoGarantiaDTO);
        return new ResponseEntity<>(contratoCreated, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Se modifica un contrato de garantia a partir de su id", response = DepartamentoDTO.class, tags = "ContratoGarantia")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ContratoGarantiaDTO contratoGarantiaDTO) {
        Optional<ContratoGarantiaDTO> contratoCreated = contratoGarantiaService.update(contratoGarantiaDTO, id);
        return new ResponseEntity<>(contratoCreated, HttpStatus.OK);
    }

    @ApiOperation(value = "Se elimina un contrato de garantia a partir de su id", response = DepartamentoDTO.class, tags = "ContratoGarantia")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        contratoGarantiaService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todas los contratos de garantias", response = DepartamentoDTO.class, tags = "ContratosGarantias")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        contratoGarantiaService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
