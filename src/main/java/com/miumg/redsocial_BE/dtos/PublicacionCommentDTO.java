package com.miumg.redsocial_BE.dtos;

import lombok.Data;

@Data
public class PublicacionCommentDTO {
    private String description;
    private Integer userId;
    private Integer publicacionId;
}
