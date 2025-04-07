package com.example.demo.observer;

import com.example.demo.model.Curso;
import org.springframework.stereotype.Service;

@Service
public interface Observador {
    void actualizar(Curso curso, String mensaje);
}
