package com.supercharger.app.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "mecanicos")
public class Mecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(value = EnumType.STRING)
    private Especialidad especialidad;
    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;
    @Column(name = "hora_salida")
    private LocalTime horaSalida;
}
