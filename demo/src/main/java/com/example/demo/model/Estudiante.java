package com.example.demo.model;

import com.example.demo.observer.*;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

import com.example.demo.service.HistorialEstudiantesService;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@DiscriminatorValue("ESTUDIANTE")
@Setter @Getter
@NoArgsConstructor
@Table(name = "estudiantes")
public class Estudiante extends Persona implements Objetivo<Estudiante> {
    @Autowired
    private HistorialEstudiantesService historialEstudiantesService;

    @ManyToOne
    @JoinColumn(name = "id_programa", referencedColumnName = "id", nullable = false)
    private Programa programa;

    @Column(name = "codigo", nullable = false, length = 10)
    private Long codigo;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "promedio", nullable = false)
    private Double promedio;

    @Transient
    private List<Observador<Estudiante>> observadores = new ArrayList<>();

    public void agregarObservador(Observador<Estudiante> observador) {
        if (!this.observadores.contains(observador)) {
            this.observadores.add(observador);
        }
    }

    public void eliminarObservador(Observador<Estudiante> observador) {
        this.observadores.remove(observador);
    }

    public void notificarObservadores(String mensaje) {
        if (!observadores.isEmpty()) {
            for (Observador<Estudiante> observador : observadores) {
                observador.actualizar(this, mensaje);
                if (observador instanceof HistorialEstudiantes) {
                    historialEstudiantesService.guardarHistorial((HistorialEstudiantes) observador);
                }
            }
        }
    }

    public Estudiante(String nombres, String apellidos, String email, Long codigo, Boolean activo, Double promedio, Programa programa) {
        super(nombres, apellidos, email);
        this.codigo = codigo;
        this.activo = activo;
        this.promedio = promedio;
        this.programa = programa;
    }

    @Override
    public String toString() {
        return super.toString() + " Estudiante{" +
                "codigo=" + codigo +
                ", activo=" + activo +
                ", promedio=" + promedio +
                ", programa=" + programa.getNombre() +
                '}';
    }

}
