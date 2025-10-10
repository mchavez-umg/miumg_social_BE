package com.miumg.redsocial_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionResponseDTO {
    private Integer id;
    private String description;
    private String image;
    private LocalDateTime publicationDate;
    private UsuarioAmigoDTO usuario;
    private long likeCount;
    private long commentCount;
    private List<CommentWithUserDTO> comments;
}
