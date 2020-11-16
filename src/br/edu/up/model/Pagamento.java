package br.edu.up.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pagamentos")
@TableGenerator(
        name = "pagamentos_sq",
        table = "pagamentos_sq",
        pkColumnName = "id",
        valueColumnName = "seq",
        pkColumnValue = "pagamento",
        initialValue = 1,
        allocationSize = 1
)
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pagamentos_sq")
    @Column(name="id")
    private int id;

    @OneToOne
    @JoinColumn(name="idAluguel")
    private Aluguel aluguel;

    @Column(name="dtVencimento")
    private Date dtVencimento;

    @Column(name="dtPagamento")
    private Date dtPagamento;

    public Pagamento() {
    }

    public Pagamento(Aluguel aluguel, Date dtVencimento, Date dtPagamento) {
        this.aluguel = aluguel;
        this.dtVencimento = dtVencimento;
        this.dtPagamento = dtPagamento;
    }

    public int getId() {
        return id;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    @Override
    public String toString(){
        return "id=" + id +
                ", aluguel.usuario='" + getAluguel().getUsuario().getNome() + '\'' +
                ", aluguel.veiculo='" + getAluguel().getVeiculo().getCodigo() + '\'' +
                ", dtVencimento=" + getDtVencimento() +
                ", dtPagamento=" + getDtPagamento();
    }
}
