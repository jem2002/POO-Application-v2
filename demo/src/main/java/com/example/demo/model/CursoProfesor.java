package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "curso_profesor")
public class CursoProfesor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id", nullable = false, unique = true)
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id", nullable = false, unique = true)
    private Curso curso;

    @Column(name = "año", nullable = false, unique = true)
    private Integer anno;

    @Column(name = "semestre", nullable = false, unique = true)
    private Integer semestre;

    public CursoProfesor(Profesor profesor, Curso curso, Integer anno, Integer semestre) {
        this.profesor = profesor;
        this.curso = curso;
        this.anno = anno;
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "CursoProfesor{" +
                "id=" + id +
                ", profesor=" + profesor.getNombres() +
                ", curso=" + curso.getNombre() +
                ", anno=" + anno +
                ", semestre=" + semestre +
                '}';
    }
}
