package com.example.demo.observer;

public interface Observador<T> {
    void actualizar(T t, String mensaje);
}
