package com.supercharger.app.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {

    private Long id;
    private String nombre;
    private String apellido;
    private String tipoDoc;
    private String nroDoc;
    private int telefono;
    private Vehiculo vehiculo;
}
