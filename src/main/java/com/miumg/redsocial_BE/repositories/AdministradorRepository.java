package com.miumg.redsocial_BE.repositories;

import com.miumg.redsocial_BE.models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByUsername(String username);
}
