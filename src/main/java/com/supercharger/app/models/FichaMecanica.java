package com.supercharger.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FichaMecanica {
    private String actividad;
    private String insumos;
    private Constancia constancia;
    private Mecanico mecanico;
    private Cliente cliente;
    private Turno turno;
}
