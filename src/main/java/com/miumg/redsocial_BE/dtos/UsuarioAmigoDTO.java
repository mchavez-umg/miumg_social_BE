package com.miumg.redsocial_BE.dtos;

import com.miumg.redsocial_BE.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAmigoDTO {
    private int id;
    private String username;
    private String email;
    private String name;

    public UsuarioAmigoDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.email = usuario.getEmail();
        this.name = usuario.getName();
    }
}
