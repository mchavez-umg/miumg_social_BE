package com.miumg.redsocial_BE.services;

import com.miumg.redsocial_BE.models.Administrador;
import com.miumg.redsocial_BE.models.Usuario;
import com.miumg.redsocial_BE.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdministradorService {
    @Autowired
    AdministradorRepository administradorRepository;

    public boolean authenticateUser(String username, String password) {
        Optional<Administrador> administrador = administradorRepository.findByUsername(username);
        if (administrador.isPresent()) {
            Administrador administrador1 = administrador.get();
            return administrador1.getPassword().equals(password);
        }
        return false;
    }

    public ArrayList<Administrador> getAdministrador(){
        return (ArrayList<Administrador>) administradorRepository.findAll();
    }

    public Optional<Administrador> getById(Integer id){
        return administradorRepository.findById(id);
    }

    public Administrador saveAdministrador(Administrador noticia){
        return administradorRepository.save(noticia);
    }

    public Administrador updateByID(Administrador request, Integer id ){
        Administrador usuario = administradorRepository.findById(id).get();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(request.getPassword());
        administradorRepository.save(usuario);
        return usuario;
    }

    public Boolean deleteAdministrador(Integer id){
        if (administradorRepository.existsById(id)) {
            administradorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
