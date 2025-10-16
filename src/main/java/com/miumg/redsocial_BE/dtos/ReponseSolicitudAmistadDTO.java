package com.miumg.redsocial_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseSolicitudAmistadDTO {
    private Integer id;
    private UsuarioAmigoDTO remitente;
    private Integer destinatarioId;
    private Integer statusId;
}
