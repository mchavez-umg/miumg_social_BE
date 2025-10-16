package com.miumg.redsocial_BE.services;

import com.miumg.redsocial_BE.dtos.ReponseSolicitudAmistadDTO;
import com.miumg.redsocial_BE.dtos.SolicitudAmistadDTO;
import com.miumg.redsocial_BE.dtos.UsuarioAmigoDTO;
import com.miumg.redsocial_BE.models.EstadoSolicitudAmistad;
import com.miumg.redsocial_BE.models.SolicitudAmistad;
import com.miumg.redsocial_BE.models.Usuario;
import com.miumg.redsocial_BE.repositories.EstadoSolicitudAmistadRepository;
import com.miumg.redsocial_BE.repositories.SolicitudAmistadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitudAmistadService {
    @Autowired
    private SolicitudAmistadRepository solicitudAmistadRepository;

    @Autowired
    private EstadoSolicitudAmistadRepository estadoSolicitudAmistadRepository;

    public List<SolicitudAmistadDTO> getSolicitudes() {
        return solicitudAmistadRepository.findAll()
                .stream()
                .map(s -> new SolicitudAmistadDTO(
                        s.getId(),
                        s.getUsuarioRemitente().getId(),
                        s.getUsuarioDestinatario().getId(),
                        s.getStatus().getId()))
                .collect(Collectors.toList());
    }

    public List<ReponseSolicitudAmistadDTO> getSolicitudesRecibidas(Usuario destinatario) {
        return solicitudAmistadRepository.findByUsuarioDestinatario(destinatario)
                .stream()
                .map(s -> new ReponseSolicitudAmistadDTO(
                        s.getId(),
                        new UsuarioAmigoDTO(
                                s.getUsuarioRemitente().getId(),
                                s.getUsuarioRemitente().getUsername(),
                                s.getUsuarioRemitente().getEmail(),
                                s.getUsuarioRemitente().getName()
                        ),
                        s.getUsuarioDestinatario().getId(),
                        s.getStatus().getId()))
                .collect(Collectors.toList());
    }

    public SolicitudAmistad sendSolicitudAmistad(SolicitudAmistad solicitudAmistad){
        Optional<SolicitudAmistad> existente = solicitudAmistadRepository
                .findByUsuarioRemitenteAndUsuarioDestinatario(
                        solicitudAmistad.getUsuarioRemitente(),
                        solicitudAmistad.getUsuarioDestinatario()
                );

        if (existente.isPresent()) {
            throw new RuntimeException("Ya existe una solicitud enviada a este usuario");
        }
        return solicitudAmistadRepository.save(solicitudAmistad);
    }

    public SolicitudAmistad updateStatusSolicitud(SolicitudAmistadDTO solicitudAmistadDTO, Integer id) {
        SolicitudAmistad solicitudAmistad = solicitudAmistadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        EstadoSolicitudAmistad estado = estadoSolicitudAmistadRepository.findById(solicitudAmistadDTO.getStatusId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        solicitudAmistad.setStatus(estado);
        return solicitudAmistadRepository.save(solicitudAmistad);
    }

}
