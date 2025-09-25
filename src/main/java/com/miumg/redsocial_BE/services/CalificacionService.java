package com.miumg.redsocial_BE.services;

import com.miumg.redsocial_BE.models.Calificacion;
import com.miumg.redsocial_BE.repositories.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;

    public Optional<Calificacion> getById(Integer id){
        return calificacionRepository.findById(id);
    }

    public ArrayList<Calificacion> getCalificacion(){
        return (ArrayList<Calificacion>) calificacionRepository.findAll();
    }

    public ArrayList<Calificacion> getCalificacionByName(String nombre){
        return (ArrayList<Calificacion>) calificacionRepository.findCalificacionByNombre(nombre);
    }

    public Calificacion saveCalificacion(Calificacion calificacion){
        return calificacionRepository.save(calificacion);
    }

    public Calificacion updateByID(Calificacion request, Integer id ){
        Calificacion calificacion = calificacionRepository.findById(id).get();
        calificacion.setNota(request.getNota());
        calificacion.setNombre(request.getNombre());
        calificacionRepository.save(calificacion);
        return calificacion;
    }

    public Boolean deleteCalificacion(Integer id){
        try {
            calificacionRepository.deleteById(id);
            return  true;
        }catch (Exception e){
            return false;
        }
    }
}
