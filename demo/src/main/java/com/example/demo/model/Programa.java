package com.example.demo.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "programa")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_facultad", referencedColumnName = "id", nullable = false)
    private Facultad facultad;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "registro", nullable = false)
    private Date registro;

}
