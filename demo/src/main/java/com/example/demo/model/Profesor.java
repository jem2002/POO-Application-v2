package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("PROFESOR")
@Setter @Getter
@NoArgsConstructor
@Table(name = "profesores")
public class Profesor extends Persona {

    @Column(name = "tipo_contrato", nullable = false)
    private String tipoContrato;

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
