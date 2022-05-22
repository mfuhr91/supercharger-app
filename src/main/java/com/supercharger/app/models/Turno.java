package com.supercharger.app.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Turno {

    private Long id;
    private Mecanico mecanico;
    private LocalDateTime fecha;
    private Cliente cliente;
    private boolean disponible;
    private LocalDateTime asistencia;

}
