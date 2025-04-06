package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "facultad")
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToOne
    @JoinColumn(name = "id_decano", referencedColumnName = "id", nullable = false, unique = true)
    private Profesor decano;

    public Facultad(String nombre, Profesor decano) {
        this.nombre = nombre;
        this.decano = decano;
    }

    @Override
    public String toString() {
        return "Facultad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", decano=" + decano +
                '}';
    }

}
