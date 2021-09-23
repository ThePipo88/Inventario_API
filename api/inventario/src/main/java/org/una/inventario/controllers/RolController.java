package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.dto.TransaccionDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Rol;
import org.una.inventario.services.IRolService;
import org.una.inventario.services.ITransaccionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@Api(tags = {"Roles"})
public class RolController {

    @Autowired
    private IRolService rolService;

    @ApiOperation(value = "Obtiene un departamento a partir de su id", response = DepartamentoDTO.class, tags = "Roles")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<RolDTO> rolFound = rolService.findById(id);
        return new ResponseEntity<>(rolFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un rol a partir de su estado", response = RolDTO.class, tags = "Roles")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        Optional<List<RolDTO>> result = rolService.findByEstado(estado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/byFechaCreacion/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de roles de acuerdo a su fecha de creacion", response = RolDTO.class, responseContainer = "RolesDTO", tags = "Roles")
    public ResponseEntity<?> findByFechaCreacionBetween(@PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate") Date endDate) {
        Optional<List<RolDTO>> result = rolService.findByFechaCreacionBetween(startDate,endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Se crea un nuevo rol", response = RolDTO.class, tags = "Rol")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody RolDTO rolDTO) {
        RolDTO rolCreated = rolService.create(rolDTO);
        return new ResponseEntity<>(rolCreated, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Se modifica un rol a partir de su id", response = RolDTO.class, tags = "Rol")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RolDTO rolModified) {
        Optional<RolDTO> rolUpdated = rolService.update(rolModified, id);
        return new ResponseEntity<>(rolUpdated, HttpStatus.OK);
    }


    @ApiOperation(value = "Se elimina un rol a partir de su id", response = RolDTO.class, tags = "Rol")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        rolService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Se eliminan todos los roles", response = RolDTO.class, tags = "Rol")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        rolService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
