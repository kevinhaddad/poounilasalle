package model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Curso {

    @Id
    private String codigo;

    private String nome;

    private int cargaHoraria;

    @ManyToMany(mappedBy = "cursos")
    private Set<Aluno> alunos;

    // Construtores, getters e setters

    public Curso() {
    }

    public Curso(String codigo, String nome, int cargaHoraria, Set<Aluno> alunos) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.alunos = alunos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
