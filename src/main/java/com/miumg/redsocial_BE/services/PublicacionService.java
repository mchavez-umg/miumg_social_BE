package com.miumg.redsocial_BE.services;

import com.miumg.redsocial_BE.dtos.PublicacionDTO;
import com.miumg.redsocial_BE.models.Publicacion;

import com.miumg.redsocial_BE.repositories.PublicacionLikeRepository;
import com.miumg.redsocial_BE.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PublicacionService {
    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired
    PublicacionLikeRepository publicacionLikeRepository;

    public ArrayList<Publicacion> getPublicacion(){
        return (ArrayList<Publicacion>) publicacionRepository.findAll();
    }

    public Optional<Publicacion> getById(Integer id){
        return publicacionRepository.findById(id);
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
