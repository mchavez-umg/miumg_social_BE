package com.miumg.redsocial_BE.repositories;

import com.miumg.redsocial_BE.models.PublicacionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicacionLikeRepository extends JpaRepository<PublicacionLike, Integer> {
    long countByPublicacion_Id(Integer publicacionId);
    long count();
    Optional<PublicacionLike> findByUsuarioIdAndPublicacionId(Integer usuarioId, Integer publicacionId);
}
