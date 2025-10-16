package com.miumg.redsocial_BE.repositories;

import com.miumg.redsocial_BE.models.SolicitudAmistad;
import com.miumg.redsocial_BE.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudAmistadRepository extends JpaRepository<SolicitudAmistad, Integer> {
    //Encontrar si existe solicitud
    Optional<SolicitudAmistad> findByUsuarioRemitenteAndUsuarioDestinatario(Usuario usuarioRemitente, Usuario usuarioDestinatario);
    //Encontrar si existe solicitud para eliminar
    Optional<SolicitudAmistad> findByUsuarioRemitenteIdAndUsuarioDestinatarioIdAndStatusId(
            Integer remitenteId,
            Integer destinatarioId,
            Integer statusId
    );

    List<SolicitudAmistad> findByUsuarioDestinatarioAndStatusId(Usuario usuarioDestinatario, Integer statusId);
    // Solicitudes aceptadas donde soy destinatario
    List<SolicitudAmistad> findByUsuarioDestinatarioIdAndStatusId(Integer destinatarioId, Integer statusId);
    // Solicitudes aceptadas donde soy remitente
    List<SolicitudAmistad> findByUsuarioRemitenteIdAndStatusId(Integer remitenteId, Integer statusId);


}
