package com.ebac.modulo65.repository;

import com.ebac.modulo65.dto.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
}
