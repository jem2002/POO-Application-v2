package com.example.demo.model;

import com.example.demo.observer.*;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

import com.example.demo.service.HistorialProfesoresService;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@DiscriminatorValue("PROFESOR")
@Setter @Getter
@NoArgsConstructor
@Table(name = "profesores")
public class Profesor extends Persona implements Objetivo<Profesor> {
    @Autowired
    private HistorialProfesoresService historialProfesoresService;

    @Column(name = "tipo_contrato", nullable = false)
    private String tipoContrato;

    @Transient
    private List<Observador<Profesor>> observadores = new ArrayList<>();

    public void agregarObservador(Observador<Profesor> observador) {
        if (!this.observadores.contains(observador)) {
            this.observadores.add(observador);
        }
    }

    public void eliminarObservador(Observador<Profesor> observador) {
        this.observadores.remove(observador);
    }

    public void notificarObservadores(String mensaje) {
        if (!observadores.isEmpty()) {
            for (Observador<Profesor> observador : observadores) {
                observador.actualizar(this, mensaje);
                if (observador instanceof HistorialProfesores) {
                    historialProfesoresService.guardarHistorial((HistorialProfesores) observador);
                }
            }
        }
    }

    public Profesor(String nombre, String apellidos, String email, String tipoContrato) {
        super(nombre, apellidos, email);
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return super.toString() + " Profesor{" +
                "tipoContrato='" + tipoContrato + '\'' +
                '}';
    }

}
