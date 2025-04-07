package com.example.demo.factory;

import com.example.demo.model.Curso;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Facultad;
import com.example.demo.model.Profesor;
import com.example.demo.model.Programa;
import java.sql.Date;
import java.util.Map;

public class EntidadFactoryImpl implements EntidadFactory {

    private static final String TIPO_ESTUDIANTE = "estudiante";
    private static final String TIPO_CURSO = "curso";
    private static final String TIPO_PROFESOR = "profesor";
    private static final String TIPO_PROGRAMA = "programa";
    private static final String TIPO_FACULTAD = "facultad";

    @Override
    public Object crearEntidad(String tipo, Map<String, Object> atributos) {
        switch (tipo.toLowerCase()) {
            case TIPO_ESTUDIANTE:
                return crearEstudiante(atributos);
            case TIPO_CURSO:
                return crearCurso(atributos);
            case TIPO_PROFESOR:
                return crearProfesor(atributos);
            case TIPO_PROGRAMA:
                return crearPrograma(atributos);
            case TIPO_FACULTAD:
                return crearFacultad(atributos);
            default:
                throw new IllegalArgumentException("Tipo de entidad no soportado: " + tipo);
        }
    }

    private Estudiante crearEstudiante(Map<String, Object> atributos) {
        validarParametros(atributos, "nombre", "apellidos", "email", "programa", "codigo", "activo", "promedio");
        return new Estudiante(
            (String) atributos.get("nombre"),
            (String) atributos.get("apellidos"),
            (String) atributos.get("email"),
            (Programa) atributos.get("programa"),
            (Long) atributos.get("codigo"),
            (Boolean) atributos.get("activo"),
            (Double) atributos.get("promedio")
        );
    }

    private Curso crearCurso(Map<String, Object> atributos) {
        validarParametros(atributos, "nombre", "Programa", "activo");
        return new Curso(
            (String) atributos.get("nombre"),
            (Programa) atributos.get("Programa"),
            (Boolean) atributos.get("activo")
        );
    }

    private Profesor crearProfesor(Map<String, Object> atributos) {
        validarParametros(atributos, "nombre", "apellidos", "email", "tipoContrato");
        return new Profesor(
            (String) atributos.get("nombre"),
            (String) atributos.get("apellidos"),
            (String) atributos.get("email"),
            (String) atributos.get("tipoContrato")
        );
    }

    private Programa crearPrograma(Map<String, Object> atributos) {
        validarParametros(atributos, "facultad", "nombre", "duracion", "registro");
        return new Programa(
            (Facultad) atributos.get("facultad"),
            (String) atributos.get("nombre"),
            (Integer) atributos.get("duracion"),
            (Date) atributos.get("registro")
        );
    }

    private Facultad crearFacultad(Map<String, Object> atributos) {
        validarParametros(atributos, "nombre", "decano");
        return new Facultad(
            (String) atributos.get("nombre"),
            (Profesor) atributos.get("decano")
        );
    }

    private void validarParametros(Map<String, Object> atributos, String... clavesRequeridas) {
        for (String clave : clavesRequeridas) {
            if (!atributos.containsKey(clave) || atributos.get(clave) == null) {
                throw new IllegalArgumentException("Falta el parámetro requerido: " + clave);
            }
        }
    }
}