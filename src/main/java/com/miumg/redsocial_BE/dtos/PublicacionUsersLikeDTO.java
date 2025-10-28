package com.miumg.redsocial_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionUsersLikeDTO {
    private Integer id;
    private LocalDateTime likedDate;
    private Integer usuarioId;
    private String username;
    private String name;
    private String email;
}
