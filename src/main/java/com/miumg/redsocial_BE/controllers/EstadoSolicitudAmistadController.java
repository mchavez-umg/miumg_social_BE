package com.miumg.redsocial_BE.controllers;

import com.miumg.redsocial_BE.models.EstadoSolicitudAmistad;
import com.miumg.redsocial_BE.services.EstadoSolicitudAmistadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitud/status")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoSolicitudAmistadController {
    @Autowired
    private EstadoSolicitudAmistadService estadoSolicitudAmistadService;

    @GetMapping()
    public ArrayList<EstadoSolicitudAmistad> getEstadoSolicitudAmistad(){
        return this.estadoSolicitudAmistadService.getEstadoSolicitudAmistad();
    }

    @GetMapping(path = "/{id}")
    public Optional<EstadoSolicitudAmistad> getById(@PathVariable Integer id){
        return this.estadoSolicitudAmistadService.getById(id);
    }

    @PostMapping("")
    public EstadoSolicitudAmistad saveEstadoSolicitudAmistad(@RequestBody EstadoSolicitudAmistad usuario){
        return this.estadoSolicitudAmistadService.saveEstadoSolicitudAmistad(usuario);
    }

    @PutMapping(path = "/{id}")
    public EstadoSolicitudAmistad updateEstadoSolicitudAmistadById(@RequestBody EstadoSolicitudAmistad request ,@PathVariable Integer id){
        return this.estadoSolicitudAmistadService.updateByID(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        boolean ok = this.estadoSolicitudAmistadService.deleteEstadoSolicitudAmistad(id);
        if (ok){
            return "EstadoSolicitudAmistad eliminado";
        }else{
            return "Error en la eliminación";
        }
    }
}
