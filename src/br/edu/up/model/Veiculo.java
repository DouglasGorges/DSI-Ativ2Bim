package br.edu.up.model;

import javax.persistence.*;

@Entity
@Table(name = "veiculos")
@TableGenerator(
        name = "veiculos_sq",
        table = "veiculos_sq",
        pkColumnName = "id",
        valueColumnName = "seq",
        pkColumnValue = "veiculo_id",
        initialValue = 1,
        allocationSize = 1
)
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "veiculos_sq")
    @Column(name="id")
    private int id;

    @Column(name="codigo")
    private Integer codigo;

    @Column(name="cor")
    private String cor;

    @Column(name="preco")
    private double preco;

    @Column(name = "idLocado")
    private int idLocado;

    public Veiculo() {
    }

    public Veiculo(Integer codigo, String cor, double preco, int idLocado) {
        this.codigo = codigo;
        this.cor = cor;
        this.preco = preco;
        this.idLocado = idLocado;
    }

    public int getId() {
        return id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
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

    public boolean getIdLocado() {
        return (idLocado == 0 ? false : true);
    }

    public void setIdLocado(boolean idLocado) {
        if (idLocado) {
            this.idLocado = 1;
        } else {
            this.idLocado = 0;
        }
    }

    @Override
    public String toString(){
        return "id=" + id +
                ", codigo=" + getCodigo() +
                ", cor='" + getCor() + '\'' +
                ", preco=" + getPreco();
    }
}
