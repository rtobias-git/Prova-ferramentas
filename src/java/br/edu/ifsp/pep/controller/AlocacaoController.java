package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.AlocacaoDAO;
import br.edu.ifsp.pep.dao.DesenvolvedorDAO;
import br.edu.ifsp.pep.dao.ProjetoDAO;
import br.edu.ifsp.pep.entidade.Alocacao;
import br.edu.ifsp.pep.entidade.Desenvolvedor;
import br.edu.ifsp.pep.entidade.Projeto;
import br.edu.ifsp.pep.entidade.StatusAlocacao;
import br.edu.ifsp.pep.util.Mensagem;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AlocacaoController {

    @Inject
    private AlocacaoDAO alocacaoDAO;

    @Inject
    private ProjetoDAO projetoDAO;

    @Inject
    private DesenvolvedorDAO desenvolvedorDAO;

    private BigDecimal valorHora;
    private Integer quantidadeHorasSemanais;
    private Alocacao alocacao = new Alocacao();
    private List<Alocacao> alocacoes;
    private StatusAlocacao statusSelecionado;
    private Desenvolvedor desenvolvedorSelecionado;
    private Projeto projetoSelecionado;

    public List<Alocacao> getAlocacoes(){
        if (this.alocacoes == null) {
            this.alocacoes = alocacaoDAO.buscarTodas();
        }
        return this.alocacoes;
    }

    public void filtrarPorStatus() {
        alocacoes = alocacaoDAO.buscarPeloStatus(statusSelecionado);
    }

    public void filtrarPorDesenvolvedor() {
        alocacoes = alocacaoDAO.buscarPeloDesenvolvedor(desenvolvedorSelecionado);
    }

    public List<Projeto> buscarTodosProjetos() {
        return projetoDAO.buscarTodos();
    }

    public List<Desenvolvedor> buscarTodosDesenvolvedores() {
        return desenvolvedorDAO.buscarTodos();
    }

    public void finalizar(Alocacao alocacao) {
        try {
            alocacaoDAO.finalizar(alocacao);
            alocacoes = null;
            Mensagem.sucesso("Alocacao Finalizada.");
        } catch (Exception ex) {
            ex.printStackTrace();
            Mensagem.erro(ex.getMessage());
        }
    }

    public void inserir() {
        try {
            alocacao.setDesenvolvedor(desenvolvedorSelecionado);
            alocacao.setProjeto(projetoSelecionado);
            alocacao.setQuantidadeDeHoras(quantidadeHorasSemanais);
            alocacao.setValorDaHora(valorHora);
            alocacao.setStatus(StatusAlocacao.Ativa);
            alocacaoDAO.inserir(alocacao);
            Mensagem.sucesso("Alocacao concluida.");

            //Iniciar nova alocacao
            alocacao = new Alocacao();
            projetoSelecionado = null;
            projetoSelecionado = null;
            valorHora = null;
            quantidadeHorasSemanais = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            Mensagem.erro(ex.getMessage());
        }
    }

    public Alocacao getAlocacao() {
        return alocacao;
    }

    public void setAlocacao(Alocacao alocacao) {
        this.alocacao = alocacao;
    }

    public void setAlocacoes(List<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }

    public StatusAlocacao getStatusSelecionado() {
        return statusSelecionado;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public Integer getQuantidadeHorasSemanais() {
        return quantidadeHorasSemanais;
    }

    public void setQuantidadeHorasSemanais(Integer quantidadeHorasSemanais) {
        this.quantidadeHorasSemanais = quantidadeHorasSemanais;
    }

    public void setStatusSelecionado(StatusAlocacao statusSelecionado) {
        this.statusSelecionado = statusSelecionado;
    }

    public Desenvolvedor getDesenvolvedorSelecionado() {
        return desenvolvedorSelecionado;
    }

    public void setDesenvolvedorSelecionado(Desenvolvedor desenvolvedorSelecionado) {
        this.desenvolvedorSelecionado = desenvolvedorSelecionado;
    }

    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }
}
