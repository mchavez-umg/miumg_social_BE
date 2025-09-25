package com.miumg.redsocial_BE.services;

import com.miumg.redsocial_BE.models.EstadoSolicitudAmistad;
import com.miumg.redsocial_BE.repositories.EstadoSolicitudAmistadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EstadoSolicitudAmistadService {
    @Autowired
    EstadoSolicitudAmistadRepository estadoSolicitudAmistadRepository;

    public ArrayList<EstadoSolicitudAmistad> getEstadoSolicitudAmistad(){
        return (ArrayList<EstadoSolicitudAmistad>) estadoSolicitudAmistadRepository.findAll();
    }

    public Optional<EstadoSolicitudAmistad> getById(Integer id){
        return estadoSolicitudAmistadRepository.findById(id);
    }

    public EstadoSolicitudAmistad saveEstadoSolicitudAmistad(EstadoSolicitudAmistad noticia){
        return estadoSolicitudAmistadRepository.save(noticia);
    }

    public EstadoSolicitudAmistad updateByID(EstadoSolicitudAmistad request, Integer id ){
        EstadoSolicitudAmistad estadoSolicitud = estadoSolicitudAmistadRepository.findById(id).get();
        estadoSolicitud.setDescription(request.getDescription());
        estadoSolicitudAmistadRepository.save(estadoSolicitud);
        return estadoSolicitud;
    }

    public Boolean deleteEstadoSolicitudAmistad(Integer id){
        try {
            estadoSolicitudAmistadRepository.deleteById(id);
            return  true;
        }catch (Exception e){
            return false;
        }
    }
}
