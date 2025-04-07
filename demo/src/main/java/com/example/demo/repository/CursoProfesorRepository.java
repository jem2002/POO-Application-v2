package com.example.demo.repository;

import com.example.demo.config.DatabaseConnection;
import com.example.demo.model.CursoProfesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoProfesorRepository implements ServiciosCrud<CursoProfesor>, ServiciosInformes<CursoProfesor> {

    private final DatabaseConnection databaseConnection;

    public CursoProfesorRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void inscribir(CursoProfesor cursoProfesor) {
        String sql = "INSERT INTO curso_profesor (id_profesor, id_curso, año, semestre) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cursoProfesor.getProfesor().getId());
            statement.setLong(2, cursoProfesor.getCurso().getId());
            statement.setInt(3, cursoProfesor.getAnno());
            statement.setInt(4, cursoProfesor.getSemestre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(CursoProfesor cursoProfesor) {
        String sql = "DELETE FROM curso_profesor WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cursoProfesor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(CursoProfesor cursoProfesor) {
        String sql = "UPDATE curso_profesor SET id_profesor = ?, id_curso = ?, año = ?, semestre = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cursoProfesor.getProfesor().getId());
            statement.setLong(2, cursoProfesor.getCurso().getId());
            statement.setInt(3, cursoProfesor.getAnno());
            statement.setInt(4, cursoProfesor.getSemestre());
            statement.setLong(5, cursoProfesor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarInformacion(CursoProfesor cursoProfesor) {
        inscribir(cursoProfesor);
    }

    @Override
    public void cargarDatos() {
        System.out.println("Cargando datos...");
    }

    @Override
    public Integer cantidadActual() {
        String sql = "SELECT COUNT(*) FROM curso_profesor";
        try (Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<CursoProfesor> imprimirListado() {
        List<CursoProfesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso_profesor";
        try (Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                CursoProfesor cursoProfesor = new CursoProfesor();
                cursoProfesor.setId(resultSet.getLong("id"));
                // cursoProfesor.setProfesor(...);
                // cursoProfesor.setCurso(...);
                cursoProfesor.setAnno(resultSet.getInt("año"));
                cursoProfesor.setSemestre(resultSet.getInt("semestre"));
                lista.add(cursoProfesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
