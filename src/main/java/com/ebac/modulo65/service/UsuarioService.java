package com.ebac.modulo65.service;

import com.ebac.modulo65.dto.Usuario;
import com.ebac.modulo65.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    //Post
    public Usuario crearUsuario(Usuario usuario) throws Exception {
        if( usuario.getEdad() >= 18 ) {
            return usuarioRepository.save(usuario);
        }
        throw new Exception("El usuario tiene que ser mayor de edad");
    }

    //GetById
    public Optional<Usuario> obtenerUsuarioPorId(Long id_usuario) {
        return usuarioRepository.findById(id_usuario);
    }

    //GetAll
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    //Put
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    //Delete
    public void eliminarUsuario(Long id_usuario) {
        usuarioRepository.deleteById(id_usuario);
    }
}
