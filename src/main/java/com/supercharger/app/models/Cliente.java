package com.supercharger.app.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(name = "tipo_doc")
    private String tipoDoc;
    @Column(name = "nro_doc")
    private String nroDoc;
    private Long telefono;

    @ManyToOne()
    private Vehiculo vehiculo;
}
