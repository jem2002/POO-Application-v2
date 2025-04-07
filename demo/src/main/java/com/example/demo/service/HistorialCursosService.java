package com.example.demo.service;

import com.example.demo.model.Curso;
import com.example.demo.observer.*;
import com.example.demo.repository.HistorialCursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialCursosService {

    @Autowired
    private HistorialCursosRepository historialCursosRepository;

    public void guardarHistorial(HistorialCursos historialCursos) {
        historialCursosRepository.save(historialCursos);
    }

    public void notificarYGuardar(Curso curso, String mensaje) {
        HistorialCursos historial = new HistorialCursos();
        historial.actualizar(curso, mensaje);
        guardarHistorial(historial);
    }
}
