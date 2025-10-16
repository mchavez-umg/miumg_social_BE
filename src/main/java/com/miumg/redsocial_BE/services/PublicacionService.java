package com.miumg.redsocial_BE.services;

import com.miumg.redsocial_BE.dtos.*;
import com.miumg.redsocial_BE.models.Publicacion;

import com.miumg.redsocial_BE.repositories.PublicacionCommentRepository;
import com.miumg.redsocial_BE.repositories.PublicacionLikeRepository;
import com.miumg.redsocial_BE.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicacionService {
    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired
    PublicacionLikeRepository publicacionLikeRepository;
    @Autowired
    PublicacionCommentRepository publicacionCommentRepository;
    @Autowired
    PublicacionCommentService publicacionCommentService;

    public List<PublicacionResponseDTO> getPublicaciones() {
        return publicacionRepository.findAll()
                .stream()
                .map(pub -> {
                    long likeCount = publicacionLikeRepository.countByPublicacion_Id(pub.getId());
                    long commentsCount = publicacionCommentRepository.countByPublicacion_Id(pub.getId());
                    List<CommentWithUserDTO> comments = publicacionCommentService.getCommentsByPublicacionId(pub.getId());

                    return new PublicacionResponseDTO(
                            pub.getId(),
                            pub.getDescription(),
                            pub.getImage(),
                            pub.getPublicationDate(),
                            new UsuarioAmigoDTO(pub.getUsuario()),
                            likeCount,
                            commentsCount,
                            comments
                    );
                })
                .collect(Collectors.toList());
    }

    public Optional<Publicacion> getById(Integer id){
        return publicacionRepository.findById(id);
    }

    public List<PublicacionResponseDTO> getPublicacionesByUsuarioId(Integer usuarioId) {
        return publicacionRepository.findByUsuario_Id(usuarioId)
                .stream()
                .map(pub -> {
                    long likeCount = publicacionLikeRepository.countByPublicacion_Id(pub.getId());
                    long commentsCount = publicacionCommentRepository.countByPublicacion_Id(pub.getId());
                    List<CommentWithUserDTO> comments = publicacionCommentService.getCommentsByPublicacionId(pub.getId());

                    return new PublicacionResponseDTO(
                            pub.getId(),
                            pub.getDescription(),
                            pub.getImage(),
                            pub.getPublicationDate(),
                            new UsuarioAmigoDTO(pub.getUsuario()),
                            likeCount,
                            commentsCount,
                            comments
                    );
                })
                .toList();
    }

    public List<Publicacion> searchByDescription(String description){
        return publicacionRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public Publicacion savePublicacion(Publicacion publicacion){
        return publicacionRepository.save(publicacion);
    }

    public Publicacion updateByID(PublicacionDTO publicacionDTO, Integer id ){
        Publicacion publicacion = publicacionRepository.findById(id).get();
        publicacion.setDescription(publicacionDTO.getDescription());
        publicacion.setImage(publicacionDTO.getImage());
        publicacionRepository.save(publicacion);
        return publicacion;
    }

    public Boolean deletePublicacion(Integer id){
        try {
            publicacionRepository.deleteById(id);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

    public long getLikeCount(Integer publicacionId) {
        return publicacionLikeRepository.countByPublicacion_Id(publicacionId);
    }

    public long getAllLikes() {
        return publicacionLikeRepository.count();
    }


}
