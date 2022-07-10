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
@Table(name = "trabajos")
public class Trabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Especialidad especialidad;
    private LocalTime tiempo;

    @OneToOne()
    private FichaMecanica fichaMecanica;
}
