package com.supercharger.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    private String modelo;
    private String marca;
    private int nroPoliza;
    private Seguro seguro;
}
