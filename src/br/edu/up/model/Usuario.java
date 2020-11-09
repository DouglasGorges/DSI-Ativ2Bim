package br.edu.up.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuarios")
@TableGenerator(
        name = "usuarios_sq",
        table = "sqlite_sequence",
        pkColumnName = "id",
        valueColumnName = "seq",
        pkColumnValue = "usuario",
        initialValue = 1,
        allocationSize = 1
)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "usuarios_sq")
    private int id;
    private String nome;
    private String sobrenome;
    private Date nascimento;

    public Usuario() {
    }

    public Usuario(String nome, String sobrenome, Date nascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nascimento = nascimento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
}
