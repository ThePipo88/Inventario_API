package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.dto.ParametroDTO;
import org.una.inventario.dto.ProveedorDTO;
import org.una.inventario.services.IParametroService;
import org.una.inventario.services.IProveedorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
@Api(tags = {"Proveedores"})
public class ProveedoresController {

    @Autowired
    private IProveedorService proveedorService;
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Obtiene una lista de todos las proveedores", response = ProveedorDTO.class, responseContainer = "List", tags = "Proveedores")
    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ProveedorDTO>> result = proveedorService.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Obtiene un poveedor a partir de su id", response = ProveedorDTO.class,  responseContainer = "List", tags = "Proveedor")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<ProveedorDTO> proveedorFound = proveedorService.findById(id);
            return new ResponseEntity<>(proveedorFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Obtiene uns lidts de proveedores a partir de su nombre", response = ProveedorDTO.class, tags = "Proveedores")
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ProveedorDTO>> result = proveedorService.findByNombre(term);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Obtiene un poveedor a partir de su id", response = ProveedorDTO.class, tags = "Proveedor")
    @GetMapping("/{telefono}")
    public ResponseEntity<?> findByTelefono(@PathVariable(value = "telefono") String telefono) {
        try {
            Optional<ProveedorDTO> proveedorFound = proveedorService.findByTelefono(telefono);
            return new ResponseEntity<>(proveedorFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Obtiene un poveedor a partir de su correo", response = ProveedorDTO.class, tags = "Proveedor")
    @GetMapping("/{correo}")
    public ResponseEntity<?> findByCorreo(@PathVariable(value = "correo") String correo) {
        try {
            Optional<ProveedorDTO> proveedorFound = proveedorService.findByCorreo(correo);
            return new ResponseEntity<>(proveedorFound, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Obtiene una lista de categorias a partir de su estado", response = ProveedorDTO.class, responseContainer = "List", tags = "Proveedores")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        try {
            Optional<List<ProveedorDTO>> result = proveedorService.findByEstado(estado);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un proveedor", response = ProveedorDTO.class, tags = "Proveedor")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ProveedorDTO proveedorDTO) {
        try {
            Optional<ProveedorDTO> proveedorCreated = proveedorService.create(proveedorDTO);
            return new ResponseEntity<>(proveedorCreated, HttpStatus.CREATED);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Se modifica un proveedor a partir de su id", response = ProveedorDTO.class, tags = "Proveedor")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ProveedorDTO proveedorDTO) {
        try {
            Optional<ProveedorDTO> proovedorUpdated = proveedorService.update(proveedorDTO, id);
            return new ResponseEntity<>(proovedorUpdated, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Se elimina un proveedor partir de su id", response = ProveedorDTO.class, tags = "Proveedor")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            proveedorService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('AUDITOR')")
    @ApiOperation(value = "Se eliminan todos las proveedores", response = ProveedorDTO.class, tags = "Proveedor")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            proveedorService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
