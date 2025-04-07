package com.example.demo.model;

import com.example.demo.observer.*;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("ESTUDIANTE")
@Setter @Getter
@NoArgsConstructor
@Table(name = "estudiantes")
public class Estudiante extends Persona implements Objetivo<Estudiante> {

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
            }
        }
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
