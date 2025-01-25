package com.ebac.modulo65.repository;

import com.ebac.modulo65.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
