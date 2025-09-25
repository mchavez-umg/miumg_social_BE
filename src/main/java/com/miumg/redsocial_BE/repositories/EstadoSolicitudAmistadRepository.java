package com.miumg.redsocial_BE.repositories;

import com.miumg.redsocial_BE.models.EstadoSolicitudAmistad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoSolicitudAmistadRepository extends JpaRepository<EstadoSolicitudAmistad,Integer> {
}
