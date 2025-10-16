package com.miumg.redsocial_BE.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="publicacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private Integer id;

    @Column(name="description")
    private String description;

    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;

    @Column(name="publicationDate")
    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PublicacionLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PublicacionComment> comments = new ArrayList<>();

}
