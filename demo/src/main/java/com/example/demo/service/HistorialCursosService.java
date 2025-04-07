package com.example.demo.service;

import com.example.demo.observer.*;
import com.example.demo.repository.HistorialCursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialCursosService {

    @Autowired
    private HistorialCursosRepository historialCursosRepository;

    public void guardarHistorial(Observador observador) {
        historialCursosRepository.save(observador);
    }
}
