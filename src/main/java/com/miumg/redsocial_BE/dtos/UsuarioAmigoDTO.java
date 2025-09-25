package com.miumg.redsocial_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAmigoDTO {
    private Integer id;
    private String username;
    private String email;
    private String name;
}
