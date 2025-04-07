package com.example.demo.observer;

public interface Objetivo<T> {

    void agregarObservador(Observador<T> observador);
    void eliminarObservador(Observador<T> observador);
    void notificarObservadores(String mensaje);

}
