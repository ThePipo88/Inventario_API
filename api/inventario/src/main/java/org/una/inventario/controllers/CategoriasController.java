package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.*;
import org.una.inventario.services.IAlertaService;
import org.una.inventario.services.ICategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
@Api(tags = {"Categorias"})
public class CategoriasController {

    @Autowired
    private ICategoriaService categoriaService;

    @ApiOperation(value = "Obtiene una lista de todos las categorias", response = ActivoDTO.class, responseContainer = "List", tags = "Categorias")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<CategoriaDTO>> result = categoriaService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una categoria a partir de su id", response = ActivoDTO.class,  responseContainer = "List", tags = "Categoria")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<CategoriaDTO> categoriaFound = categoriaService.findById(id);
        return new ResponseEntity<>(categoriaFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una categoria a partir de su nombre", response = ActivoDTO.class, tags = "Categoria")
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        Optional<List<CategoriaDTO>> result = categoriaService.findByNombre(term);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de categorias a partir de su estado", response = ActivoDTO.class, responseContainer = "List", tags = "Categorias")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        Optional<List<CategoriaDTO>> result = categoriaService.findByEstado(estado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea una categoria", response = ActivoAsignadoDTO.class, tags = "Categoria")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody CategoriaDTO categoriaDTO) {
        Optional<CategoriaDTO> categoriaCreated = categoriaService.create(categoriaDTO);
        return new ResponseEntity<>(categoriaCreated, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Se modifica una categoria a partir de su id", response = DepartamentoDTO.class, tags = "Categoria")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody CategoriaDTO categoriaDTO) {
        Optional<CategoriaDTO> categoriaUpdated = categoriaService.update(categoriaDTO, id);
        return new ResponseEntity<>(categoriaUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Se elimina una categoria a partir de su id", response = DepartamentoDTO.class, tags = "Categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        categoriaService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todas las categorias", response = DepartamentoDTO.class, tags = "Categorias")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        categoriaService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
