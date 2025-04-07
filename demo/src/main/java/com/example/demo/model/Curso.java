package com.example.demo.model;

import com.example.demo.observer.*;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "curso")
public class Curso implements Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_programa", referencedColumnName = "id", nullable = false)
    private Programa programa;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    private List<Observador> observadores;

    
    public Curso(String nombre, Programa programa, Boolean activo) {
        this.nombre = nombre;
        this.programa = programa;
        this.activo = activo;
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(Observador observador) {
        if (!this.observadores.contains(observador)) {
            this.observadores.add(observador);
        }
    }

    public void eliminarObservador(Observador observador) {
        this.observadores.remove(observador);
    }

    public void notificarObservadores(String mensaje) {
        if (!observadores.isEmpty()) {
            for (Observador observador : observadores) {
                observador.actualizar(this, mensaje);
            }
        }
    }

    @Override
    public String toString() {
        return " Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", programa=" + programa.getNombre() +
                ", activo=" + activo +
                '}';
    }
}
