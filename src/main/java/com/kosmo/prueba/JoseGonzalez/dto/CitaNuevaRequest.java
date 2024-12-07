package com.kosmo.prueba.JoseGonzalez.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaNuevaRequest {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long doctorId;
    private Long consultorioId;
    private String hora;
    private String fecha;
}
