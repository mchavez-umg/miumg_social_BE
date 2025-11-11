package com.miumg.redsocial_BE.services;
import com.miumg.redsocial_BE.models.Publicacion;
import com.miumg.redsocial_BE.models.PublicacionLike;
import com.miumg.redsocial_BE.models.Usuario;
import com.miumg.redsocial_BE.repositories.PublicacionLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionLikeService {
    @Autowired
    private PublicacionLikeRepository publicacionLikeRepository;

    public PublicacionLike toggleLike(Usuario usuario, Publicacion publicacion) {
        Optional<PublicacionLike> existingLike =
                publicacionLikeRepository.findByUsuarioIdAndPublicacionId(usuario.getId(), publicacion.getId());

        if (existingLike.isPresent()) {
            publicacionLikeRepository.delete(existingLike.get());
            return null;
        } else {
            PublicacionLike newLike = new PublicacionLike();
            newLike.setUsuario(usuario);
            newLike.setType(1);
            newLike.setPublicacion(publicacion);
            newLike.setLikedDate(LocalDateTime.now());
            return publicacionLikeRepository.save(newLike);
        }
    }

    public List<PublicacionLike> getInfoUsersByPublicacionId(Integer id){
        return publicacionLikeRepository.findByPublicacion_Id(id);
    }

    public PublicacionLike toggleNoLike(Usuario usuario, Publicacion publicacion) {
        Optional<PublicacionLike> existingLike =
                publicacionLikeRepository.findByUsuarioIdAndPublicacionId(usuario.getId(), publicacion.getId());

        if (existingLike.isPresent()) {
            publicacionLikeRepository.delete(existingLike.get());
            return null;
        } else {
            PublicacionLike newLike = new PublicacionLike();
            newLike.setUsuario(usuario);
            newLike.setType(0);
            newLike.setPublicacion(publicacion);
            newLike.setLikedDate(LocalDateTime.now());
            return publicacionLikeRepository.save(newLike);
        }
    }

}
