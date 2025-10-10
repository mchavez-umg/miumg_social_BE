package com.miumg.redsocial_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionLikeDTO {
    private Integer userId;
    private Integer publicacionId;
}
