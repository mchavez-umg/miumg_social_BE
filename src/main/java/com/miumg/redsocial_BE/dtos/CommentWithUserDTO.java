package com.miumg.redsocial_BE.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentWithUserDTO {
    private Integer id;
    private String description;
    private UsuarioAmigoDTO usuario;
    private LocalDateTime commentDate;
}
