package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "inscripcion")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id", nullable = false, unique = true)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id", nullable = false, unique = true)
    private Curso curso;

    @Column(name = "año", nullable = false, unique = true)
    private Integer año;

    @Column(name = "semestre", nullable = false, unique = true)
    private Integer semestre;

    public Inscripcion(Estudiante estudiante, Curso curso, Integer año, Integer semestre) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.año = año;
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", estudiante=" + estudiante.toString() +
                ", curso=" + curso.getNombre() +
                ", año=" + año +
                ", semestre=" + semestre +
                '}';
    }
}
