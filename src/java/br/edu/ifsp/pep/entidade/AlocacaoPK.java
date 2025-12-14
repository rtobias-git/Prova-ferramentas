package br.edu.ifsp.pep.entidade;

import java.io.Serializable;
import java.util.Objects;

public class AlocacaoPK implements Serializable {

    private Integer desenvolvedor;

    private Integer projeto;

    public Integer getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(Integer desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public Integer getProjeto() {
        return projeto;
    }

    public void setProjeto(Integer projeto) {
        this.projeto = projeto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.desenvolvedor);
        hash = 53 * hash + Objects.hashCode(this.projeto);
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
        final AlocacaoPK other = (AlocacaoPK) obj;
        if (!Objects.equals(this.desenvolvedor, other.desenvolvedor)) {
            return false;
        }
        return Objects.equals(this.projeto, other.projeto);
    }

}
