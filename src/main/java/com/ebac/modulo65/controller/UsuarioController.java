package com.ebac.modulo65.controller;

import com.ebac.modulo65.dto.Usuario;
import com.ebac.modulo65.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Slf4j: Notación lombok de implementación y registro logback
@Slf4j
@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    //Post
    @PostMapping("/usuarios")
    public ResponseWrapper<Usuario> crearUsuario(@RequestBody Usuario usuario){
        log.info("Agregando Nuevo Usuario");

        try {
            Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
            ResponseEntity<Usuario> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);

            return new ResponseWrapper<>(true, "Usuario Creado", responseEntity);
        } catch (Exception e) {
            ResponseEntity<Usuario> responseEntity = ResponseEntity.badRequest().build();

            return new ResponseWrapper<>(false, e.getMessage(), responseEntity);
        }
    }

    //GetById
    @GetMapping("/usuarios/{id}")
    public ResponseWrapper<Usuario> obtenerUsuario(@PathVariable Long id) {
        //{}: Declaración de variables ({} {}, var1, var2)
        log.info("Obteniendo Usuario por id {}", id);

        Optional<Usuario> usuarioOptional = usuarioService.obtenerUsuarioPorId(id);

        if( usuarioOptional.isPresent() ) {
            Usuario usuario = usuarioOptional.get();
            ResponseEntity<Usuario> responseEntity = ResponseEntity.ok(usuario);

            return new ResponseWrapper<>(true, "Información Usuario " + id, responseEntity);
        } else {
            ResponseEntity<Usuario> responseEntity = ResponseEntity.notFound().build();

            return new ResponseWrapper<>(false, "Error en obtención " + id, responseEntity);
        }
    }

    //GetAll
    @GetMapping("/usuarios")
    public ResponseWrapper< List<Usuario> > obtenerUsuarios() {
        log.info("Obteniendo Lista de Usuarios");

        List<Usuario> usuarioList =  usuarioService.obtenerUsuarios();
        ResponseEntity< List<Usuario> > responseEntity = ResponseEntity.ok(usuarioList);

        return new ResponseWrapper<>(true, "Listado de Usuarios", responseEntity);
    }

    //Put
    @PutMapping("/usuarios/{id}")
    public ResponseWrapper<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        log.info("Actualizando Usuario por id {}", id);

        Optional<Usuario> usuarioOptional = usuarioService.obtenerUsuarioPorId(id);

        if( usuarioOptional.isPresent() ) {
            usuarioActualizado.setId_usuario(id);
            usuarioService.actualizarUsuario(usuarioActualizado);

            ResponseEntity<Usuario> responseEntity = ResponseEntity.ok(usuarioActualizado);

            return new ResponseWrapper<>(true, "Usuario actualizado", responseEntity);
        } else {
            ResponseEntity<Usuario> responseEntity = ResponseEntity.notFound().build();

            return new ResponseWrapper<>(false, "Error en obtención " + id, responseEntity);
        }
    }

    //Delete
    @DeleteMapping("/usuarios/{id}")
    public ResponseWrapper<Void> eliminarUsuario(@PathVariable Long id) {
        log.info("Eliminando Usuario por id {}", id);

        usuarioService.eliminarUsuario(id);

        ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();

        return new ResponseWrapper<>(true, "Usuario eliminado", responseEntity);
    }

}
