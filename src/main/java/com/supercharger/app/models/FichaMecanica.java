package com.supercharger.app.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "fichas_mecanicas")
public class FichaMecanica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actividad;
    private String insumos;

    @ManyToOne
    private Constancia constancia;

    @ManyToOne
    private Mecanico mecanico;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Turno turno;

}
