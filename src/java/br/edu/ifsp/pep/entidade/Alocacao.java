package br.edu.ifsp.pep.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@IdClass(AlocacaoPK.class)
@Table(name = "alocacao")
@NamedQueries(value = {
    @NamedQuery(name = "Alocacao.buscarTodas", query = "FROM Alocacao a"),
    @NamedQuery(name = "Alocacao.buscarPeloStatus", query = "FROM Alocacao a WHERE a.status = :status"),
    @NamedQuery(name = "Alocacao.buscarPeloDesenvolvedor", query = "FROM Alocacao a WHERE a.desenvolvedor = :desenvolvedor"),
    @NamedQuery(name = "Alocacao.validarSituacaoDesenvolvedor", query = "FROM Alocacao a WHERE a.desenvolvedor = :desenvolvedor AND a.status = :status")
})
public class Alocacao implements Serializable {

    @Id
    @JoinColumn(name = "desenvolvedor_id", nullable = false)
    private Desenvolvedor desenvolvedor;

    @Id
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @Column(name = "valor_da_hora", precision = 8, scale = 2, nullable = false)
    private BigDecimal valorDaHora;

    @Column(name = "quantidade_de_horas", nullable = false)
    private Integer quantidadeDeHoras;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 12, nullable = false)
    private StatusAlocacao status;

    public void AlteraStatus(StatusAlocacao status) throws Exception {
        if (this.status.equals(status)) {
            throw new Exception("Não foi possivel concluir esse processo, já que o status dessa alocação é: " + this.status.toString());
        } else {
            this.status = status;
        }
    }

    public Desenvolvedor getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public BigDecimal getValorDaHora() {
        return valorDaHora;
    }

    public void setValorDaHora(BigDecimal valorDaHora) {
        this.valorDaHora = valorDaHora;
    }

    public Integer getQuantidadeDeHoras() {
        return quantidadeDeHoras;
    }

    public void setQuantidadeDeHoras(Integer quantidadeDeHoras) {
        this.quantidadeDeHoras = quantidadeDeHoras;
    }

    public StatusAlocacao getStatus() {
        return status;
    }

    public void setStatus(StatusAlocacao status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.desenvolvedor);
        hash = 67 * hash + Objects.hashCode(this.projeto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alocacao other = (Alocacao) obj;
        if (!Objects.equals(this.desenvolvedor, other.desenvolvedor)) {
            return false;
        }
        return Objects.equals(this.projeto, other.projeto);
    }
}
