package com.supercharger.app.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Agenda {

    private Long id;
    private Mecanico mecanico;
    private Turno turno;
    private Cliente cliente;

}
