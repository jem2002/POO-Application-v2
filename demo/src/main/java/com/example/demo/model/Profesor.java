package com.example.demo.model;

import com.example.demo.observer.*;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("PROFESOR")
@Setter @Getter
@NoArgsConstructor
@Table(name = "profesores")
public class Profesor extends Persona implements Objetivo<Profesor> {

    @Column(name = "tipo_contrato", nullable = false)
    private String tipoContrato;

    @Transient
    private List<Observador<Profesor>> observadores = new ArrayList<>();

    public Profesor(String nombre, String apellidos, String email, String tipoContrato) {
        this.nombres = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.tipoContrato = tipoContrato;
    }

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
            }
        }
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "tipoContrato='" + tipoContrato + '\'' +
                ", observadores=" + observadores +
                ", id=" + id +
                ", nombre='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
