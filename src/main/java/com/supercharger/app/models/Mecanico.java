package com.supercharger.app.models;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mecanico {

    private Long id;
    private String nombre;
    private Especialidad especialidad;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
}
