package com.example.demo.service;

import com.example.demo.model.Profesor;
import com.example.demo.observer.*;
import com.example.demo.repository.HistorialProfesoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialProfesoresService {
    
    @Autowired
    private HistorialProfesoresRepository historialProfesoresRepository;

    public void guardarHistorial(HistorialProfesores historialProfesores) {
        historialProfesoresRepository.save(historialProfesores);
    }
    
    public void notificarYGuardar(Profesor profesor, String mensaje) {
        HistorialProfesores historial = new HistorialProfesores();
        historial.actualizar(profesor, mensaje);
        guardarHistorial(historial);
    }
}
