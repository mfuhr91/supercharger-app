package com.supercharger.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mecanico {

    private String nombre;
    private Especialidad especialidad;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
}
