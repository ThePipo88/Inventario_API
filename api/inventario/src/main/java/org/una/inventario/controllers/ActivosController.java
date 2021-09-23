package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.*;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Categoria;
import org.una.inventario.entities.Marca;
import org.una.inventario.entities.Proveedor;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoRepository;
import org.una.inventario.services.IActivoService;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/activos")
@Api(tags = {"Activos"})
public class ActivosController {

    @Autowired
    private IActivoService activoService;

    @ApiOperation(value = "Obtiene una lista de todos los activos", response = ActivoDTO.class, responseContainer = "List", tags = "Activos")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ActivoDTO>> result = activoService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un activo a partir de su id", response = ActivoDTO.class,  responseContainer = "List", tags = "Activo")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ActivoDTO> activoFound = activoService.findById(id);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un activo a partir de su nombre", response = ActivoDTO.class, tags = "Activo")
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        Optional<List<ActivoDTO>> result = activoService.findByNombre(term);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de activos a partir de su estado", response = ActivoDTO.class,responseContainer = "List", tags = "Activo")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        Optional<List<ActivoDTO>> result = activoService.findByEstado(estado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de categorias a partir de su id", response = ActivoDTO.class, responseContainer = "List", tags = "Categoria")
    @GetMapping("/{categoria}")
    public ResponseEntity<?> findByCategoria(@PathVariable(value = "categoria") Long categoria) {
        Optional<List<CategoriaDTO>> result = activoService.findByCategoria(categoria);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de marcas a partir de su id", response = MarcaDTO.class, responseContainer = "List", tags = "Marca")
    @GetMapping("/{marca}")
    public ResponseEntity<?> findByMarca(@PathVariable(value = "marca") Long marca) {
        Optional<List<MarcaDTO>> result = activoService.findByMarca(marca);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una proveedores a partir de su id", response = ProveedorDTO.class, responseContainer = "List", tags = "Proveedor")
    @GetMapping("/{proveedor}")
    public ResponseEntity<?> findByProveedor(@PathVariable(value = "proveedor") Long proveedor) {
        Optional<List<ProveedorDTO>> result = activoService.findByProveedor(proveedor);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un activo dentro de otro a partir de su id", response = ProveedorDTO.class, tags = "Activo")
    @GetMapping("/{continente}")
    public ResponseEntity<?> findByContinente(@PathVariable(value = "continente") Long continente) {
        Optional<ActivoDTO> result = activoService.findByContinente(continente);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un activo", response = ActivoAsignadoDTO.class, tags = "Activo")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ActivoDTO activoDTO) {
        Optional<ActivoDTO> activoCreated = activoService.create(activoDTO);
        return new ResponseEntity<>(activoCreated, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Se modifica un activo a partir de su id", response = DepartamentoDTO.class, tags = "Activo")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ActivoDTO activoDTO) {
        Optional<ActivoDTO> activoUpdated = activoService.update(activoDTO, id);
        return new ResponseEntity<>(activoUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Se elimina un activo a partir de su id", response = DepartamentoDTO.class, tags = "Activo")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        activoService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todos los activos", response = DepartamentoDTO.class, tags = "Activo")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        activoService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

}