package com.miumg.redsocial_BE.controllers;

import com.miumg.redsocial_BE.models.Administrador;
import com.miumg.redsocial_BE.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody Administrador administrador) {
        boolean isAuthenticated = administradorService.authenticateUser(administrador.getUsername(), administrador.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping()
    public ArrayList<Administrador> getAdministrador(){
        return this.administradorService.getAdministrador();
    }

    @GetMapping(path = "/{id}")
    public Optional<Administrador> getById(@PathVariable Integer id){
        return this.administradorService.getById(id);
    }

    @GetMapping("/search")
    public List<Administrador> searchAdmin(@RequestParam String username) {
        return administradorService.searchAdmin(username);
    }


    @PostMapping("")
    public Administrador saveAdministrador(@RequestBody Administrador usuario){
        return this.administradorService.saveAdministrador(usuario);
    }

    @PutMapping(path = "/{id}")
    public Administrador updateAdministradorById(@RequestBody Administrador request ,@PathVariable Integer id){
        return this.administradorService.updateByID(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        boolean eliminado = administradorService.deleteAdministrador(id);
        if (eliminado) {
            return ResponseEntity.ok("Administrador eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el administrador con ID: " + id);
        }
    }

}
