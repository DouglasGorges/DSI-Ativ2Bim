package br.edu.up.model;

import javax.persistence.*;

@Entity
@Table(name = "veiculos")
@TableGenerator(
        name = "veiculos_sq",
        table = "sqlite_sequence",
        pkColumnName = "id",
        valueColumnName = "seq",
        pkColumnValue = "veiculo",
        initialValue = 1,
        allocationSize = 1
)
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "veiculos_sq")
    private int id;
    private long codigo;
    private String cor;
    private double preco;

    public Veiculo() {
    }

    public Veiculo(long codigo, String cor, double preco) {
        this.codigo = codigo;
        this.cor = cor;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
