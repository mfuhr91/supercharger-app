package com.supercharger.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Especialidad {

    FRENOS(1, "FRENOS","FRENOS"),
    ELECTRICIDAD(2, "ELECTRICIDAD","ELECTRICIDAD"),
    TREN_DELANTERO_AMORTIGUACION(3, "TREN DELANTERO Y AMORTIGUACION","TREN_DELANTERO_AMORTIGUACION"),
    ENCENDIDO_CARBURACION(4, "ENCENDIDO Y CARBURACION","ENCENDIDO_CARBURACION"),
    CHAPA_PINTURA(5, "CHAPA Y PINTURA","CHAPA_PINTURA"),
    MECANICA_GENERAL(6, "MECANICA EN GENERAL","MECANICA_GENERAL");

    int id;
    String nombre;
    String valor;

    public static Especialidad getByNombre(String nombre) {
        for (Especialidad especialidad : Especialidad.values()) {
            if (especialidad.getNombre().equals(nombre)) {
                return especialidad;
            }
        }
        return null;
    }

}

