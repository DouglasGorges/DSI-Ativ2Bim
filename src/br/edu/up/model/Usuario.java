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
    @Column(name="id")
    private int id;

    @Column(name="nome")
    private String nome;

    @Column(name="sobrenome")
    private String sobrenome;

    @Column(name="dtNascimento")
    private Date dtNascimento;

    public Usuario() {
    }

    public Usuario(String nome, String sobrenome, Date dtNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dtNascimento = dtNascimento;
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

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @Override
    public String toString(){
        return "id=" + id +
                ", nome='" + getNome() + '\'' +
                ", sobrenome='" + getSobrenome() + '\'' +
                ", dtNasimento=" + getDtNascimento();
    }
}
