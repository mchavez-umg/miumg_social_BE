package com.miumg.redsocial_BE.controllers;

import com.miumg.redsocial_BE.dtos.CommentWithUserDTO;
import com.miumg.redsocial_BE.dtos.PublicacionCommentDTO;
import com.miumg.redsocial_BE.dtos.PublicacionDTO;
import com.miumg.redsocial_BE.dtos.PublicacionLikeDTO;
import com.miumg.redsocial_BE.models.*;
import com.miumg.redsocial_BE.services.PublicacionCommentService;
import com.miumg.redsocial_BE.services.PublicacionLikeService;
import com.miumg.redsocial_BE.services.PublicacionService;
import com.miumg.redsocial_BE.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publicacion")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PublicacionLikeService publicacionLikeService;

    @Autowired
    private PublicacionCommentService publicacionCommentService;

    @GetMapping()
    public ArrayList<Publicacion> getPublicaciones(){
        return this.publicacionService.getPublicacion();
    }

    @GetMapping(path = "/{id}")
    public Optional<Publicacion> getPublicacionById(@PathVariable Integer id){
        return this.publicacionService.getById(id);
    }

    @GetMapping(path = "/user/{id}")
    public List<Publicacion> getPublicacionByUsuarioId(@PathVariable Integer id){
        return this.publicacionService.getByUsuarioId(id);
    }

    @GetMapping("/search")
    public List<Publicacion> searchPost(@RequestParam String description) {
        return publicacionService.searchByDescription(description);
    }

    @PostMapping("")
    public Publicacion savePublicacion(@RequestBody PublicacionDTO dto) {
        Publicacion publicacion = new Publicacion();
        publicacion.setDescription(dto.getDescription());
        publicacion.setImage(dto.getImage());
        publicacion.setPublicationDate(LocalDateTime.now());

        Usuario usuario = usuarioService.getById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        publicacion.setUsuario(usuario);

        return publicacionService.savePublicacion(publicacion);
    }

    @PutMapping(path = "/{id}")
    public Publicacion updatePublicacionById(@RequestBody PublicacionDTO dto ,@PathVariable Integer id){
        return this.publicacionService.updateByID(dto, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        boolean ok = this.publicacionService.deletePublicacion(id);
        if (ok){
            return "Publicacion eliminada";
        }else{
            return "Error en la eliminación";
        }
    }

    @GetMapping("/{id}/likes/count")
    public long getLikeCount(@PathVariable Integer id) {
        return publicacionService.getLikeCount(id);
    }
    @GetMapping("likes/total")
    public long getTotalLikes() {
        return publicacionService.getAllLikes();
    }

    @PostMapping("/like")
    public ResponseEntity<?> toggleLike(@RequestBody PublicacionLikeDTO dto) {
        Usuario usuario = usuarioService.getById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionService.getById(dto.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        PublicacionLike result = publicacionLikeService.toggleLike(usuario, publicacion);

        if (result == null) {
            return ResponseEntity.ok("Like eliminado");
        } else {
            return ResponseEntity.ok("Like agregado");
        }
    }

    @PostMapping("/comment")
    public PublicacionComment saveComment(@RequestBody PublicacionCommentDTO dto) {
        PublicacionComment commentPost = new PublicacionComment();
        commentPost.setCommentDate(LocalDateTime.now());
        Usuario usuario = usuarioService.getById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionService.getById(dto.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        commentPost.setDescription(dto.getDescription());
        commentPost.setUsuario(usuario);
        commentPost.setPublicacion(publicacion);
        return publicacionCommentService.saveComment(commentPost);
    }

    @GetMapping("/{id}/comment")
    public List<CommentWithUserDTO> getComments(@PathVariable Integer id) {
        return publicacionCommentService.getCommentsByPublicacionId(id);
    }

    @DeleteMapping(path = "/{id}/comment")
    public String deleteCommentById(@PathVariable("id") Integer id){
        boolean ok = this.publicacionCommentService.deleteComment(id);
        if (ok){
            return "Comentario eliminado";
        }else{
            return "Error en la eliminación";
        }
    }

}
