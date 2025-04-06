package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("ESTUDIANTE")
@Setter @Getter
@NoArgsConstructor
@Table(name = "estudiantes")
public class Estudiante extends Persona{

    @ManyToOne
    @JoinColumn(name = "id_programa", referencedColumnName = "id", nullable = false)
    private Programa programa;

    @Column(name = "codigo", nullable = false, length = 10)
    private Long codigo;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "promedio", nullable = false)
    private Double promedio;

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
