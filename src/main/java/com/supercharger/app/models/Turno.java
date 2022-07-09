package com.supercharger.app.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mecanico mecanico;
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    private boolean disponible;
    private LocalDateTime asistencia;

}
