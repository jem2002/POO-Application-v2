package com.example.demo.factory;
import java.util.Map;

public interface EntidadFactory {
    Object crearEntidad(String tipo, Map<String, Object> atributos);
}