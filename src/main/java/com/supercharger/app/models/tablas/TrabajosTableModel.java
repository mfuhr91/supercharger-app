package com.supercharger.app.models.tablas;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrabajosTableModel {

    private String especialidad;
    private String cliente;
    private String mecanico;
    private String tiempo;
}
