package com.supercharger.app.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String marca;

    @Column(name = "nro_poliza")
    private Long nroPoliza;

    @ManyToOne
    private Seguro seguro;
}
