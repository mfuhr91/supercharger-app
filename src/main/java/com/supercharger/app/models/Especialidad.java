package com.supercharger.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Especialidad {

    FRENOS(1, "FRENOS"),
    ELECTRICIDAD(2, "ELECTRICIDAD"),
    TREN_DELANTERO_AMORTIGUACION(3, "TREN DELANTERO Y AMORTIGUACION"),
    ENCENDIDO_CARBURACION(4, "ENCENDIDO Y CARBURACION"),
    CHAPA_PINTURA(5, "CHAPA Y PINTURA"),
    MECANICA_GENERAL(6, "MECANICA EN GENERAL");

    int id;
    String nombre;

    public static Especialidad getByNombre(String nombre) {
        for (Especialidad especialidad : Especialidad.values()) {
            if (especialidad.getNombre().equals(nombre)) {
                return especialidad;
            }
        }
        return null;
    }

}

