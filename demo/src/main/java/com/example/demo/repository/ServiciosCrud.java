package com.example.demo.repository;

import org.springframework.stereotype.Service;

@Service
public interface ServiciosCrud<T> {

    public void inscribir(T t);
    public void eliminar(T t);
    public void actualizar(T t);
    public void guardarInformacion(T t);
    public void cargarDatos();
}
