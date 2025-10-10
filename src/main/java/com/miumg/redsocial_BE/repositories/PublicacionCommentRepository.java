package com.miumg.redsocial_BE.repositories;

import com.miumg.redsocial_BE.models.PublicacionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionCommentRepository extends JpaRepository<PublicacionComment, Integer> {
    List<PublicacionComment> findByPublicacion_Id(Integer id);
    long countByPublicacion_Id(Integer publicacionId);

}
