package com.miumg.redsocial_BE.repositories;

import com.miumg.redsocial_BE.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByUsernameContainingIgnoreCase(String username);
    List<Usuario> findByNameContainingIgnoreCase(String username);
}
