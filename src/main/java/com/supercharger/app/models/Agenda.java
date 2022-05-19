package com.supercharger.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {

    private Mecanico mecanico;
    private Turno turno;
    private Cliente cliente;

}
