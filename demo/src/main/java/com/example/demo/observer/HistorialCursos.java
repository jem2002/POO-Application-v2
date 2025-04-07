package com.example.demo.observer;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.Instant;
import com.example.demo.model.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "historial_cursos")
public class HistorialCursos implements Observador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id", nullable = false)
    private Curso curso;

    @Column(name = "fecha_cambio")
    private Timestamp fechaCambio;

    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public void actualizar(Curso curso, String mensaje) {
        this.curso = curso;
        this.descripcion = mensaje;
        this.fechaCambio = Timestamp.from(Instant.now());
    }
}
