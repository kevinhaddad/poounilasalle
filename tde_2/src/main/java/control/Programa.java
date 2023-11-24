package control;

import model.Aluno;
import model.Curso;

import javax.persistence.*;
import java.util.*;

public class Programa {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        initializeEntityManager();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Aluno");
            System.out.println("2. Adicionar Curso");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Listar Cursos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adicionarAluno(scanner);
                    break;
                case 2:
                    adicionarCurso(scanner);
                    break;
                case 3:
                    listarAlunos();
                    break;
                case 4:
                    listarCursos();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (choice != 5);

        closeEntityManager();
    }

    private static void initializeEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("faculdade");
        entityManager = entityManagerFactory.createEntityManager();
    }

    private static void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void adicionarAluno(Scanner scanner) {
        System.out.println("Digite o nome do aluno:");
        String nomeAluno = scanner.nextLine();

        Aluno aluno = new Aluno();
        aluno.setNome(nomeAluno);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(aluno);
        transaction.commit();

        System.out.println("Aluno adicionado com sucesso!");
    }

    private static void adicionarCurso(Scanner scanner) {
        System.out.println("Digite o código do curso:");
        String codigoCurso = scanner.nextLine();

        System.out.println("Digite o nome do curso:");
        String nomeCurso = scanner.nextLine();

        System.out.println("Digite a carga horária do curso:");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Curso curso = new Curso();
        curso.setCodigo(codigoCurso);
        curso.setNome(nomeCurso);
        curso.setCargaHoraria(cargaHoraria);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(curso);
        transaction.commit();

        System.out.println("Curso adicionado com sucesso!");
    }

    private static void listarAlunos() {
        TypedQuery<Aluno> query = entityManager.createQuery("SELECT a FROM Aluno a", Aluno.class);
        List<Aluno> alunos = query.getResultList();

        if (alunos.isEmpty()) {
            System.out.println("Não há alunos cadastrados.");
        } else {
            System.out.println("Lista de Alunos:");
            for (Aluno aluno : alunos) {
                System.out.println("- " + aluno.getMatricula() + ": " + aluno.getNome());
            }
        }
    }

    private static void listarCursos() {
        TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c", Curso.class);
        List<Curso> cursos = query.getResultList();

        if (cursos.isEmpty()) {
            System.out.println("Não há cursos cadastrados.");
        } else {
            System.out.println("Lista de Cursos:");
            for (Curso curso : cursos) {
                System.out.println("- " + curso.getCodigo() + ": " + curso.getNome() + " - Carga Horária: " + curso.getCargaHoraria());
            }
        }
    }
}
