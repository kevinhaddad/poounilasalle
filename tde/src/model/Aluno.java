package model;

public class Aluno {
    private int matricula;
    private String nome;
    private int cursoCodigo;

    public Aluno(int matricula, String nome, int cursoCodigo) {
        this.matricula = matricula;
        this.nome = nome;
        this.cursoCodigo = cursoCodigo;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getCursoCodigo() {
        return cursoCodigo;
    }
}
