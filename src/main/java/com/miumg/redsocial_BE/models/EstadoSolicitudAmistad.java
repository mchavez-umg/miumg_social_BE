package com.miumg.redsocial_BE.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="status_solicitud_amistad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoSolicitudAmistad implements Serializable {

    @Serial
    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private Integer id;

    @Column(name="description")
    private String description;
}
