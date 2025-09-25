package com.miumg.redsocial_BE.controllers;

import com.miumg.redsocial_BE.models.Calificacion;
import com.miumg.redsocial_BE.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/calificacion")
@CrossOrigin(origins = "http://localhost:4200")
public class CalificacionController {
    @Autowired
    private CalificacionService calificacionService;

    @GetMapping()
    public ArrayList<Calificacion> getCalificacion(){
        return this.calificacionService.getCalificacion();
    }

    @GetMapping(path = "/{name}")
    public ArrayList<Calificacion> getCalificacionByName(@PathVariable String name){
        return this.calificacionService.getCalificacionByName(name);
    }


    @GetMapping(path = "/{id}")
    public Optional<Calificacion> getById(@PathVariable Integer id){
        return this.calificacionService.getById(id);
    }

    @PostMapping("")
    public Calificacion saveCalificacion(@RequestBody Calificacion usuario){
        return this.calificacionService.saveCalificacion(usuario);
    }

    @PutMapping(path = "/{id}")
    public Calificacion updateCalificacionById(@RequestBody Calificacion request ,@PathVariable Integer id){
        return this.calificacionService.updateByID(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        boolean eliminado = calificacionService.deleteCalificacion(id);
        if (eliminado) {
            return ResponseEntity.ok("Calificacion eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el administrador con ID: " + id);
        }
    }

}
