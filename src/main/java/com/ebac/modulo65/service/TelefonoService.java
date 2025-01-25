package com.ebac.modulo65.service;

import com.ebac.modulo65.dto.Telefono;
import com.ebac.modulo65.repository.TelefonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefonoService {

    @Autowired
    TelefonoRepository telefonoRepository;

    //Post
    public Telefono crearTelefono(Telefono telefono) throws Exception {
        if( telefono.getNumero().length() <= 10 ) {
            return telefonoRepository.save(telefono);
        }
        throw new Exception("Telefono Invalido");
    }

    //GetById
    public Optional<Telefono> obtenerTelefonoPorId(Long id_telefono) {
        return telefonoRepository.findById(id_telefono);
    }

    //GetAll
    public List<Telefono> obtenerTelefonos() {
        return telefonoRepository.findAll();
    }

    //Put
    public void actualizarTelefono(Telefono telefono) {
        telefonoRepository.save(telefono);
    }

    //Delete
    public void eliminarTelefono(Long id_telefono) {
        telefonoRepository.deleteById(id_telefono);
    }

}
