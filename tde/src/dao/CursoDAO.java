package dao;

import model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoDAO {
    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarCurso(Curso curso) throws SQLException {
        String query = "INSERT INTO cursos (codigo, nome, carga_horaria) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, curso.getCodigo());
            preparedStatement.setString(2, curso.getNome());
            preparedStatement.setInt(3, curso.getCargaHoraria());
            preparedStatement.executeUpdate();
        }
    }
}
