package com.supercharger.app.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vehiculo {

    private Long id;
    private String modelo;
    private String marca;
    private int nroPoliza;
    private Seguro seguro;
}
