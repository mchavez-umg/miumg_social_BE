package com.miumg.redsocial_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAmistadDTO {
    private Integer id;
    private Integer remitenteId;
    private Integer destinatarioId;
    private Integer statusId;
}
