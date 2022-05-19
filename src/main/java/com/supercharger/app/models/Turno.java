package com.supercharger.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turno {
    private Mecanico mecanico;
    private LocalDateTime fecha;
    private Cliente cliente;
    private boolean disponible;
    private LocalDateTime asistencia;
}
