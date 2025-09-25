package com.miumg.redsocial_BE.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="calificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calificacion implements Serializable {
    @Serial
    private static final long serialVersionUID = 8L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer id;

    @Column(name="fecha_calificacion")
    private String fechaCalificacion;

    @Column(name="nota")
    private Integer nota;

    @Column(name="nombre")
    private String nombre;
}
