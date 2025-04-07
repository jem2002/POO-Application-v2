package com.example.demo.service;

import com.example.demo.model.Estudiante;
import com.example.demo.observer.*;
import com.example.demo.repository.HistorialEstudiantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialEstudiantesService {

    @Autowired
    private HistorialEstudiantesRepository historialEstudiantesRepository;

    public void guardarHistorial(HistorialEstudiantes historialEstudiantes) {
        historialEstudiantesRepository.save(historialEstudiantes);
    }

    public void notificarYGuardar(Estudiante estudiante, String mensaje) {
        HistorialEstudiantes historial = new HistorialEstudiantes();
        historial.actualizar(estudiante, mensaje);
        guardarHistorial(historial);
    }
}
