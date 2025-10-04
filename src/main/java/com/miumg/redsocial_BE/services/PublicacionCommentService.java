package com.miumg.redsocial_BE.services;

import com.miumg.redsocial_BE.dtos.CommentWithUserDTO;
import com.miumg.redsocial_BE.dtos.UsuarioAmigoDTO;
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

    public List<CommentWithUserDTO> getCommentsByPublicacionId(Integer id){
        return publicacionCommentRepository.findByPublicacion_Id(id)
                .stream()
                .map(c -> {
                    CommentWithUserDTO dto = new CommentWithUserDTO();
                    dto.setId(c.getId());
                    dto.setUsuario(new UsuarioAmigoDTO(
                            c.getUsuario().getId(),
                            c.getUsuario().getUsername(),
                            c.getUsuario().getEmail(),
                            c.getUsuario().getName()
                    ));
                    dto.setDescription(c.getDescription());
                    dto.setCommentDate(c.getCommentDate());
                    return dto;
                })
                .toList();
    }

    public Boolean deleteComment(Integer id){
        if (publicacionCommentRepository.existsById(id)) {
            publicacionCommentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
