package br.edu.up.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pagamentos")
@TableGenerator(
        name = "pagamentos_sq",
        table = "sqlite_sequence",
        pkColumnName = "id",
        valueColumnName = "seq",
        pkColumnValue = "pagamento",
        initialValue = 1,
        allocationSize = 1
)
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pagamentos_sq")
    private int id;
    private Aluguel aluguel;
    private Date dtVencimento;
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
}
