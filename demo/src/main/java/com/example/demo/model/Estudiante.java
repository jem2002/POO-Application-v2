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

    public Estudiante(String nombres, String apellidos, String email, Programa programa, Long codigo, Boolean activo, Double promedio) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.programa = programa;
        this.codigo = codigo;
        this.activo = activo;
        this.promedio = promedio;
        notificarObservadores("Estudiante creado: " + toString());
    }

    public void modificarEstadoEstudiante(Boolean estado) {
        this.activo = estado;
        notificarObservadores("El estado del estudiante " + id + " ha sido actualizado: " + toString());
    }

    public void modificarPromedioEstudiante(Double promedio) {
        this.promedio = promedio;
        notificarObservadores("El promedio del estudiante " + id + " ha sido actualizado: " + toString());
    }

    public void modificarProgramaEstudiante(Programa programa) {
        this.programa = programa;
        notificarObservadores("El programa del estudiante " + id + " ha sido actualizado: " + toString());
    }

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

    public void eliminarEstudiante(Boolean estado) {
        this.activo = estado;
        notificarObservadores("El estado del estudiante " + id + " ha sido actualizado: " + toString());
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", codigo=" + codigo +
                ", activo=" + activo +
                ", promedio=" + promedio +
                '}';
    }

}
