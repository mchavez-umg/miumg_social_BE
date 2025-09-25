package com.miumg.redsocial_BE.services;

import com.miumg.redsocial_BE.models.PublicacionComment;
import com.miumg.redsocial_BE.repositories.PublicacionCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionCommentService {
    @Autowired
    private PublicacionCommentRepository publicacionCommentRepository;

    public PublicacionComment saveComment(PublicacionComment publicacionComment) {
        return publicacionCommentRepository.save(publicacionComment);
    }

    public List<PublicacionComment> getCommentsByPublicacionId(Integer id){
        return publicacionCommentRepository.findByPublicacion_Id(id);
    }
}
