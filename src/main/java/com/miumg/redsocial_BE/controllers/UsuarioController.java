package com.miumg.redsocial_BE.controllers;

import com.miumg.redsocial_BE.dtos.UsuarioAmigoDTO;
import com.miumg.redsocial_BE.dtos.UsuarioDTO;
import com.miumg.redsocial_BE.dtos.UsuarioResponseDTO;
import com.miumg.redsocial_BE.models.Usuario;
import com.miumg.redsocial_BE.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UsuarioDTO usuarioDTO) {
        String result = usuarioService.authenticateUser(usuarioDTO.getUsername(), usuarioDTO.getPassword());

        return switch (result) {
            case "USER_NOT_FOUND", "INVALID_PASSWORD" -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Usuario o contraseña incorrectos"));
            case "USER_DENIED" -> ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Tu cuenta ha sido desactivada. Contacta al administrador."));
            default -> {
                Integer userId = Integer.parseInt(result.replace("AUTH_SUCCESS_", ""));
                yield ResponseEntity.ok(Map.of("userId", userId));
            }
        };
    }

    @GetMapping()
    public List<UsuarioResponseDTO> getUsuarios(){
        return this.usuarioService.getUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioService.getById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @GetMapping("/search")
    public List<UsuarioAmigoDTO> searchUsers(@RequestParam String username) {
        return usuarioService.searchUser(username);
    }

    @PostMapping("")
    public Usuario saveUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return this.usuarioService.saveUsuario(usuarioDTO);
    }

    @PutMapping(path = "/{id}")
    public Usuario updateUsuarioById(@RequestBody UsuarioDTO dto ,@PathVariable Integer id){
        return this.usuarioService.updateByID(dto, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        boolean ok = this.usuarioService.deleteUsuario(id);
        if (ok){
            return "Usuario eliminado";
        }else{
            return "Error en la eliminación";
        }
    }

    @GetMapping("/amigos/{userId}")
    public ResponseEntity<List<UsuarioAmigoDTO>> getAmigos(@PathVariable Integer userId) {
        List<UsuarioAmigoDTO> amigos = usuarioService.getAmigos(userId);
        return ResponseEntity.ok(amigos);
    }

    @DeleteMapping("/amigos/{userId}/{friendId}")
    public ResponseEntity<?> eliminarAmigo(@PathVariable Integer userId, @PathVariable Integer friendId) {
        try {
            usuarioService.eliminarAmigo(userId, friendId);
            return ResponseEntity.ok("Amistad eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
