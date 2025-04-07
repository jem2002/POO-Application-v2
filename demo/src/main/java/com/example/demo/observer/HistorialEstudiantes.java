package com.example.demo.observer;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.Instant;
import com.example.demo.model.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "historial_estudiantes")
public class HistorialEstudiantes implements Observador<Estudiante> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id", nullable = false)
    private Estudiante estudiante;

    @Column(name = "fecha_cambio")
    private Timestamp fechaCambio;

    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public void actualizar(Estudiante estudiante, String mensaje) {
        this.estudiante = estudiante;
        this.descripcion = mensaje;
        this.fechaCambio = Timestamp.from(Instant.now());
    }
    
}
