package br.edu.up.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alugueis")
@TableGenerator(
        name = "alugueis_sq",
        table = "sqlite_sequence",
        pkColumnName = "id",
        valueColumnName = "seq",
        pkColumnValue = "aluguel",
        initialValue = 1,
        allocationSize = 1
)
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "alugueis_sq")
    @Column(name="id")
    private int id;

    @OneToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name="idVeiculo")
    private Veiculo veiculo;

    @Column(name="dtRetirada")
    private Date dtRetirada;

    @Column(name="dtDevolucao")
    private Date dtDevolucao;

    @Column(name="preco")
    private long preco;

    public Aluguel() {
    }

    public Aluguel(Usuario usuario, Veiculo veiculo, Date dtRetirada, Date dtDevolucao, long preco) {
        this.usuario = usuario;
        this.veiculo = veiculo;
        this.dtRetirada = dtRetirada;
        this.dtDevolucao = dtDevolucao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDtRetirada() {
        return dtRetirada;
    }

    public void setDtRetirada(Date dtRetirada) {
        this.dtRetirada = dtRetirada;
    }

    public Date getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(Date dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public long getPreco() {
        return preco;
    }

    public void setPreco(long preco) {
        this.preco = preco;
    }

    @Override
    public String toString(){
        return "id=" + id +
                ", usuario='" + getUsuario().getNome() + '\'' +
                ", veiculo='" + getVeiculo().getCodigo() + '\'' +
                ", dtRetirada=" + getDtRetirada() +
                ", dtDevolucao=" + getDtDevolucao() +
                ", preco=" + getPreco();
    }
}
