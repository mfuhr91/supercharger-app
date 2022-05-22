package com.supercharger.app.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FichaMecanica {

    private Long id;
    private String actividad;
    private String insumos;
    private Constancia constancia;
    private Mecanico mecanico;
    private Cliente cliente;
    private Turno turno;
}
