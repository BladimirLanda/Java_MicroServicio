package com.ebac.modulo65.controller;

import com.ebac.modulo65.dto.Telefono;
import com.ebac.modulo65.dto.Usuario;
import com.ebac.modulo65.service.TelefonoService;
import com.ebac.modulo65.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class TelefonoController {

    @Autowired
    TelefonoService telefonoService;

    @Autowired
    UsuarioService usuarioService;

    //Post
    @PostMapping("/telefonos")
    public ResponseWrapper<Telefono> crearTelefono(@RequestBody Telefono telefono) {
        log.info("Agregando Nuevo Telefono");

        long id_usuario = telefono.getUsuario().getId_usuario();

        Optional<Usuario> usuarioOptional = usuarioService.obtenerUsuarioPorId(id_usuario);

        if( usuarioOptional.isPresent() ) {
            try {
                Telefono telefonoCreado = telefonoService.crearTelefono(telefono);
                ResponseEntity<Telefono> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(telefonoCreado);

                return new ResponseWrapper<>(true, "Telefono Creado", responseEntity);
            } catch (Exception e) {
                ResponseEntity<Telefono> responseEntity = ResponseEntity.badRequest().build();

                return new ResponseWrapper<>(false, e.getMessage(), responseEntity);
            }
        } else {
            ResponseEntity<Telefono> responseEntity = ResponseEntity.notFound().build();

            return new ResponseWrapper<>(false, "No existe el usuario", responseEntity);
        }
    }

    //GetById
    @GetMapping("/telefonos/{id}")
    public ResponseWrapper<Telefono> obtenerTelefono(@PathVariable Long id) {
        log.info("Obteniendo Telefono por id {}", id);

        Optional<Telefono> telefonoOptional = telefonoService.obtenerTelefonoPorId(id);

        if( telefonoOptional.isPresent() ) {
            Telefono telefono = telefonoOptional.get();
            ResponseEntity<Telefono> responseEntity = ResponseEntity.ok(telefono);

            return new ResponseWrapper<>(true, "Información Telefono " + id, responseEntity);
        } else {
            ResponseEntity<Telefono> responseEntity = ResponseEntity.notFound().build();

            return new ResponseWrapper<>(false, "Error en obtención " + id, responseEntity);
        }
    }

    //GetAll
    @GetMapping("/telefonos")
    public ResponseWrapper< List<Telefono> > obtenerTelefonos() {
        log.info("Obteniendo Lista de Telefonos");

        List<Telefono> telefonoList = telefonoService.obtenerTelefonos();
        ResponseEntity< List<Telefono> > responseEntity = ResponseEntity.ok(telefonoList);

        return new ResponseWrapper<>(true, "Listado de Telefonos", responseEntity);
    }

    //Put
    @PutMapping("/telefonos/{id}")
    public ResponseWrapper<Telefono> actualizarTelefono(@PathVariable Long id, @RequestBody Telefono telefonoActualizado) {
        log.info("Actualizando Telefono por id {}", id);

        Optional<Telefono> telefonoOptional = telefonoService.obtenerTelefonoPorId(id);

        if( telefonoOptional.isPresent() ) {
            telefonoActualizado.setId_telefono(id);
            telefonoService.actualizarTelefono(telefonoActualizado);

            ResponseEntity<Telefono> responseEntity = ResponseEntity.ok(telefonoActualizado);

            return new ResponseWrapper<>(true, "Telefono actualizado", responseEntity);
        } else {
            ResponseEntity<Telefono> responseEntity = ResponseEntity.notFound().build();

            return new ResponseWrapper<>(false, "Error en obtención " + id, responseEntity);
        }
    }

    //Delete
    @DeleteMapping("/telefonos/{id}")
    public ResponseWrapper<Void> eliminarTelefono(@PathVariable Long id) {
        log.info("Eliminado Telefono por id {}", id);

        telefonoService.eliminarTelefono(id);

        ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();

        return new ResponseWrapper<>(true, "Telefono eliminado", responseEntity);
    }

}
