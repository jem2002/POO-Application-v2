package com.example.demo;

import com.example.demo.factory.EntidadFactory;
import com.example.demo.factory.EntidadFactoryImpl;
import com.example.demo.model.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EntityScan(basePackages = "com.example.demo.model")
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        EntidadFactory factory = new EntidadFactoryImpl();

        Map<String, Object> estudianteParams = new HashMap<>();
        estudianteParams.put("nombre", "Juan");
        estudianteParams.put("apellidos", "Martinez");
        estudianteParams.put("email", "juan.ejemplo@unillanos.edu.co");
        estudianteParams.put("programa", null); // Puedes reemplazar con un objeto Programa válido
        estudianteParams.put("codigo", 123456789L);
        estudianteParams.put("activo", true);
        estudianteParams.put("promedio", 4.5);
        Estudiante estudiante = (Estudiante) factory.crearEntidad("estudiante", estudianteParams);

        Map<String, Object> profesorParams = new HashMap<>();
        profesorParams.put("nombre", "Ana");
        profesorParams.put("apellidos", "Gómez");
        profesorParams.put("email", "ana.gomez@example.com");
        profesorParams.put("tipoContrato", "Tiempo Completo");
        Profesor profesor = (Profesor) factory.crearEntidad("profesor", profesorParams);

        Map<String, Object> cursoParams = new HashMap<>();
        cursoParams.put("nombre", "Matemáticas");
        cursoParams.put("Programa", null); // Puedes reemplazar con un objeto Programa válido
        cursoParams.put("activo", true);
        Curso curso = (Curso) factory.crearEntidad("curso", cursoParams);

        System.out.println(estudiante);
        System.out.println(profesor);
        System.out.println(curso);
    }
}