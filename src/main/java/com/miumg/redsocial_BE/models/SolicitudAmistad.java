package com.miumg.redsocial_BE.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="solicitud_amistad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAmistad implements Serializable {

    @Serial
    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id_remitente")
    @JsonBackReference
    private Usuario usuarioRemitente;

    @ManyToOne
    @JoinColumn(name = "usuario_id_destinatario")
    @JsonBackReference
    private Usuario usuarioDestinatario;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @JsonBackReference
    private EstadoSolicitudAmistad status;



}
