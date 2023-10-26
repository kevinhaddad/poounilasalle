package dao;

import model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarAluno(Aluno aluno) throws SQLException {
        String query = "INSERT INTO alunos (matricula, nome, curso_codigo) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, aluno.getMatricula());
            preparedStatement.setString(2, aluno.getNome());
            preparedStatement.setInt(3, aluno.getCursoCodigo());
            preparedStatement.executeUpdate();
        }
    }

    public String consultarCursoDeAluno(int matricula) throws SQLException {
        String query = "SELECT cursos.nome FROM cursos JOIN alunos ON cursos.codigo = alunos.curso_codigo WHERE alunos.matricula = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, matricula);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("nome");
                }
            }
        }
        return null;
    }

    public List<Aluno> listarAlunosDeCurso(int cursoCodigo) throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String query = "SELECT matricula, nome FROM alunos WHERE curso_codigo = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cursoCodigo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int matricula = resultSet.getInt("matricula");
                    String nome = resultSet.getString("nome");
                    alunos.add(new Aluno(matricula, nome, cursoCodigo));
                }
            }
        }
        return alunos;
    }
}
