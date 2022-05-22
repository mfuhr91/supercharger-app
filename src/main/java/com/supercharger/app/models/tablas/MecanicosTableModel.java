package com.supercharger.app.models.tablas;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MecanicosTableModel {
    private Long id;
    private String nombre;
    private String especialidad;
    private String horarios;
}
