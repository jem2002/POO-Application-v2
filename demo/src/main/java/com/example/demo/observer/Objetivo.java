package com.example.demo.observer;

import org.springframework.stereotype.Service;

@Service
public interface Objetivo {

    void agregarObservador(Observador observador);
    void eliminarObservador(Observador observador);
    void notificarObservadores(String mensaje);

}
