package control;

import dao.AlunoDAO;
import dao.CursoDAO;
import model.Aluno;
import model.Curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/escola", "postgres", "postgres")) {
            CursoDAO cursoDAO = new CursoDAO(connection);
            AlunoDAO alunoDAO = new AlunoDAO(connection);

            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Cadastrar curso");
                System.out.println("2. Cadastrar aluno");
                System.out.println("3. Consultar curso de um aluno");
                System.out.println("4. Listar alunos de um curso");
                System.out.println("5. Sair");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        cadastrarCurso(cursoDAO, scanner);
                        break;
                    case 2:
                        cadastrarAluno(alunoDAO, scanner);
                        break;
                    case 3:
                        consultarCursoDeAluno(alunoDAO, scanner);
                        break;
                    case 4:
                        listarAlunosDeCurso(alunoDAO, scanner);
                        break;
                }
            } while (opcao != 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cadastrarCurso(CursoDAO cursoDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o código do curso: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a carga horária do curso: ");
        int cargaHoraria = scanner.nextInt();
        Curso curso = new Curso(codigo, nome, cargaHoraria);
        cursoDAO.cadastrarCurso(curso);
        System.out.println("Curso cadastrado com sucesso.");
    }

    private static void cadastrarAluno(AlunoDAO alunoDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o código do curso: ");
        int cursoCodigo = scanner.nextInt();
        Aluno aluno = new Aluno(matricula, nome, cursoCodigo);
        alunoDAO.cadastrarAluno(aluno);
        System.out.println("Aluno cadastrado com sucesso.");
    }

    private static void consultarCursoDeAluno(AlunoDAO alunoDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = scanner.nextInt();
        String curso = alunoDAO.consultarCursoDeAluno(matricula);
        if (curso != null) {
            System.out.println("O aluno está matriculado no curso: " + curso);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void listarAlunosDeCurso(AlunoDAO alunoDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o código do curso: ");
        int cursoCodigo = scanner.nextInt();
        List<Aluno> alunos = alunoDAO.listarAlunosDeCurso(cursoCodigo);
        if (!alunos.isEmpty()) {
            System.out.println("Alunos do curso:");
            for (Aluno aluno : alunos) {
                System.out.println("Matrícula: " + aluno.getMatricula() + ", Nome: " + aluno.getNome());
            }
        } else {
            System.out.println("Nenhum aluno encontrado para esse curso.");
        }
    }
}
