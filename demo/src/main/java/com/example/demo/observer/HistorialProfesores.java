package com.example.demo.observer;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.Instant;
import com.example.demo.model.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "historial_profesores")
public class HistorialProfesores implements Observador<Profesor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id", nullable = false)
    private Profesor profesor;

    @Column(name = "fecha_cambio")
    private Timestamp fechaCambio;

    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public void actualizar(Profesor profesor, String mensaje) {
        this.profesor = profesor;
        this.descripcion = mensaje;
        this.fechaCambio = Timestamp.from(Instant.now());
    }

}
