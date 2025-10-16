package com.miumg.redsocial_BE.controllers;

import com.miumg.redsocial_BE.dtos.ReponseSolicitudAmistadDTO;
import com.miumg.redsocial_BE.dtos.SolicitudAmistadDTO;
import com.miumg.redsocial_BE.models.EstadoSolicitudAmistad;
import com.miumg.redsocial_BE.models.Publicacion;
import com.miumg.redsocial_BE.models.SolicitudAmistad;
import com.miumg.redsocial_BE.models.Usuario;
import com.miumg.redsocial_BE.services.EstadoSolicitudAmistadService;
import com.miumg.redsocial_BE.services.SolicitudAmistadService;
import com.miumg.redsocial_BE.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitud")
@CrossOrigin(origins = "http://localhost:4200")
public class SolicitudAmistadController {
    @Autowired
    private SolicitudAmistadService solicitudAmistadService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EstadoSolicitudAmistadService estadoSolicitudAmistadService;

    @GetMapping()
    public List<SolicitudAmistadDTO> getSolicitudes(){
        return this.solicitudAmistadService.getSolicitudes();
    }

    @GetMapping("/recibidas/{userId}")
    public ResponseEntity<?> getSolicitudesRecibidas(@PathVariable Integer userId) {
        Usuario destinatario = usuarioService.getById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<ReponseSolicitudAmistadDTO> solicitudes = solicitudAmistadService.getSolicitudesRecibidas(destinatario);

        return ResponseEntity.ok(solicitudes);
    }


    @PostMapping("")
    public ResponseEntity<?> createSolicitudAmistad(@RequestBody SolicitudAmistadDTO dto) {
        try {
            SolicitudAmistad solicitudAmistad = new SolicitudAmistad();

            Usuario usuarioRemitente = usuarioService.getById(dto.getRemitenteId())
                    .orElseThrow(() -> new RuntimeException("Usuario remitente no encontrado"));
            Usuario usuarioDestinatario = usuarioService.getById(dto.getDestinatarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario a agregar no encontrado"));
            EstadoSolicitudAmistad estado = estadoSolicitudAmistadService.getById(dto.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

            solicitudAmistad.setUsuarioRemitente(usuarioRemitente);
            solicitudAmistad.setUsuarioDestinatario(usuarioDestinatario);
            solicitudAmistad.setStatus(estado);

            return ResponseEntity.ok(solicitudAmistadService.sendSolicitudAmistad(solicitudAmistad));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping(path = "/estado/{id}")
    public SolicitudAmistad updateSolicitudAmistad(@RequestBody SolicitudAmistadDTO dto , @PathVariable Integer id){
        return this.solicitudAmistadService.updateStatusSolicitud(dto, id);
    }


}
