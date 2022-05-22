package com.supercharger.app.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Trabajo {

    private Long id;
    private Especialidad especialidad;
    private Long tiempo;
}
