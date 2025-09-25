package com.miumg.redsocial_BE.repositories;

import com.miumg.redsocial_BE.models.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    List<Calificacion> findCalificacionByNombre(String nombre);
}
