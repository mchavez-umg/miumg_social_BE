package com.miumg.redsocial_BE.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="publicacion_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionComment implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private Integer id;

    @Column(name="description")
    private String description;

    @Column(name="commentDate")
    private LocalDateTime commentDate;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    @JsonBackReference
    private Publicacion publicacion;
}
