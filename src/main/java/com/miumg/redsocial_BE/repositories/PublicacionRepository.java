package com.miumg.redsocial_BE.repositories;


import com.miumg.redsocial_BE.models.Publicacion;
import com.miumg.redsocial_BE.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    List<Publicacion> findByDescriptionContainingIgnoreCase(String description);
    List<Publicacion> findByUsuario_Id(Integer usuarioId);
}
