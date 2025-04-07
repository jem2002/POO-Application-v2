package com.example.demo.model;

import com.example.demo.observer.*;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

import com.example.demo.service.HistorialCursosService;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "curso")
public class Curso implements Objetivo<Curso> {

    @Autowired
    private HistorialCursosService historialCursosService;

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

    @Transient
    private List<Observador<Curso>> observadores = new ArrayList<>();

    
    public Curso(String nombre, Programa programa, Boolean activo) {
        this.nombre = nombre;
        this.programa = programa;
        this.activo = activo;
    }

    public void agregarObservador(Observador<Curso> observador) {
        if (!this.observadores.contains(observador)) {
            this.observadores.add(observador);
        }
    }

    public void eliminarObservador(Observador<Curso> observador) {
        this.observadores.remove(observador);
    }

    public void notificarObservadores(String mensaje) {
        if (!observadores.isEmpty()) {
            for (Observador<Curso> observador : observadores) {
                observador.actualizar(this, mensaje);
                if (observador instanceof HistorialCursos) {
                    historialCursosService.guardarHistorial((HistorialCursos) observador);
                }
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
