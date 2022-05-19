package com.supercharger.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private String nombre;
    private String apellido;
    private String tipoDoc;
    private String nroDoc;
    private int telefono;
    private Vehiculo vehiculo;
}
