package com.ebac.modulo65.controller;

import com.ebac.modulo65.dto.Usuario;
import com.ebac.modulo65.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    UsuarioService usuarioService;

    @InjectMocks
    UsuarioController usuarioController;

    @Test
    void crearUsuario() throws Exception {
        Usuario usuarioExpected = crearUsuarios(1).get(0);

        when(usuarioService.crearUsuario(usuarioExpected)).thenReturn(usuarioExpected);

        ResponseWrapper<Usuario> responseWrapper = usuarioController.crearUsuario(usuarioExpected);
        Usuario usuarioActual = responseWrapper.getResponseEntity().getBody();

        assertEquals(201, responseWrapper.getResponseEntity().getStatusCode().value());
        assertNotNull(usuarioActual);
    }

    @Test
    void crearUsuarioException() throws Exception {
        Usuario usuarioExpected = crearUsuarios(1).get(0);

        doThrow(Exception.class).when(usuarioService).crearUsuario(usuarioExpected);

        ResponseWrapper<Usuario> responseWrapper = usuarioController.crearUsuario(usuarioExpected);
        Usuario usuarioActual = responseWrapper.getResponseEntity().getBody();

        assertEquals(400, responseWrapper.getResponseEntity().getStatusCode().value());
        assertNull(usuarioActual);
    }

    @Test
    void obtenerUsuario() {
        long id_usuario = 1;
        Optional<Usuario> usuarioExpected = Optional.of(crearUsuarios(1).get(0));

        when(usuarioService.obtenerUsuarioPorId(id_usuario)).thenReturn(usuarioExpected);

        ResponseWrapper<Usuario> responseWrapper = usuarioController.obtenerUsuario(id_usuario);
        Usuario usuarioActual = responseWrapper.getResponseEntity().getBody();

        assertEquals(200, responseWrapper.getResponseEntity().getStatusCode().value());
        assertNotNull(usuarioActual);
        assertEquals("Nombre1", usuarioActual.getNombre());
    }

    @Test
    void obtenerUsuarioVoid() {
        long id_usuario = 1;

        when(usuarioService.obtenerUsuarioPorId(id_usuario)).thenReturn(Optional.empty());

        ResponseWrapper<Usuario> responseWrapper = usuarioController.obtenerUsuario(id_usuario);
        Usuario usuarioActual = responseWrapper.getResponseEntity().getBody();

        assertEquals(404, responseWrapper.getResponseEntity().getStatusCode().value());
        assertNull(usuarioActual);
    }

    @Test
    void obtenerUsuarios() {
        int usuarios = 5;
        List<Usuario> usuarioExpected = crearUsuarios(usuarios);

        when(usuarioService.obtenerUsuarios()).thenReturn(usuarioExpected);

        ResponseWrapper< List<Usuario> > responseWrapper = usuarioController.obtenerUsuarios();
        List<Usuario> usuarioActual = responseWrapper.getResponseEntity().getBody();

        assertEquals(usuarioExpected, usuarioActual);
    }

    @Test
    void obtenerUsuariosVoid() {
        when(usuarioService.obtenerUsuarios()).thenReturn(List.of());

        ResponseWrapper< List<Usuario> > responseWrapper = usuarioController.obtenerUsuarios();
        List<Usuario> usuarioActual = responseWrapper.getResponseEntity().getBody();

        assertTrue(usuarioActual.isEmpty());
        verify(usuarioService, times(1)).obtenerUsuarios();
    }

    @Test
    void actualizarUsuario() {
        long id_usuario = 1;
        String nombreActualizado = "Mirely";
        int edadActualizada = 26;

        Usuario usuarioAntiguo = new Usuario();
        usuarioAntiguo.setId_usuario(id_usuario);
        usuarioAntiguo.setNombre("Juanita");
        usuarioAntiguo.setEdad(20);

        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setNombre(nombreActualizado);
        usuarioActualizado.setEdad(edadActualizada);

        when(usuarioService.obtenerUsuarioPorId(id_usuario)).thenReturn(Optional.of(usuarioAntiguo));
        doNothing().when(usuarioService).actualizarUsuario(usuarioActualizado);

        ResponseWrapper<Usuario> responseWrapper = usuarioController.actualizarUsuario(id_usuario, usuarioActualizado);
        Usuario usuarioActual = responseWrapper.getResponseEntity().getBody();

        assertEquals(200, responseWrapper.getResponseEntity().getStatusCode().value());
        assertNotNull(usuarioActual);
        assertEquals(1, usuarioActual.getId_usuario());
    }

    @Test
    void actualizarUsuarioVoid() {
        long id_usuario = 1;
        String nombreActualizado = "Mirely";
        int edadActualizada = 26;

        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setNombre(nombreActualizado);
        usuarioActualizado.setEdad(edadActualizada);

        when(usuarioService.obtenerUsuarioPorId(id_usuario)).thenReturn(Optional.empty());

        ResponseWrapper<Usuario> responseWrapper = usuarioController.actualizarUsuario(id_usuario, usuarioActualizado);
        Usuario usuarioActual = responseWrapper.getResponseEntity().getBody();

        assertEquals(404, responseWrapper.getResponseEntity().getStatusCode().value());
        assertNull(usuarioActual);
        verify(usuarioService, never()).actualizarUsuario(usuarioActualizado);
    }

    @Test
    void eliminarUsuario() {
        long id_usuario = 1;

        doNothing().when(usuarioService).eliminarUsuario(id_usuario);

        ResponseWrapper<Void> responseWrapper = usuarioController.eliminarUsuario(id_usuario);

        assertEquals(204, responseWrapper.getResponseEntity().getStatusCode().value());
        verify(usuarioService, atLeast(1)).eliminarUsuario(id_usuario);
    }

    //HELPERS
    private List<Usuario> crearUsuarios(int elementos) {
        return IntStream.range(1, elementos+1)
                .mapToObj(i -> {
                    Usuario usuario = new Usuario();
                    usuario.setId_usuario(i);
                    usuario.setNombre("Nombre" + i);
                    usuario.setEdad(18 + i);
                    return usuario;
                }).toList();
    }
}