package com.supercharger.app.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "agendas")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mecanico mecanico;

    @ManyToOne
    private Turno turno;

    @ManyToOne
    private Cliente cliente;


}
