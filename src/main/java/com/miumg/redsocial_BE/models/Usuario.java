package com.miumg.redsocial_BE.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private Integer id;

    @Column(name="username", nullable=false )
    private String username;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="status", nullable=false)
    private Integer status;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Publicacion> publicaciones;

    @Column(name = "imageProfile", columnDefinition = "LONGTEXT")
    private String imageProfile;

    @OneToMany(mappedBy = "usuarioRemitente")
    @JsonManagedReference
    private List<SolicitudAmistad> solicitudesEnviadas;

    @OneToMany(mappedBy = "usuarioDestinatario")
    @JsonManagedReference
    private List<SolicitudAmistad> solicitudesRecibidas;

}
