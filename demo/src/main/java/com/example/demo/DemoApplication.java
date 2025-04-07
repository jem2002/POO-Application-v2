package com.example.demo;

import com.example.demo.model.Profesor;
import com.example.demo.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Crear instancias de Estudiante y Profesor
        Profesor profesor = new Profesor("Ana", "Gómez", "ana.gomez@example.com", "Tiempo Completo");

        // Guardar en la base de datos
        entityManager.persist(profesor);

        // Recuperar todas las personas
        List<Persona> personas = entityManager.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();

        // Imprimir las personas recuperadas
        System.out.println("Personas en la base de datos:");
        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }
}