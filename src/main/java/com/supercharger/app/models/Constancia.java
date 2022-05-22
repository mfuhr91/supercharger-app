package com.supercharger.app.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Constancia {

    private Long id;
    private boolean conformidad;
    private String motivo;
}
