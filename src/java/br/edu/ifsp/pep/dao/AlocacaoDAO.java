package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entidade.Alocacao;
import br.edu.ifsp.pep.entidade.Desenvolvedor;
import br.edu.ifsp.pep.entidade.StatusAlocacao;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AlocacaoDAO extends AbstractDAO<Alocacao> {

    public List<Alocacao> buscarTodas() {
        EntityManager em = getEntityManager();
        TypedQuery<Alocacao> query = em.createNamedQuery("Alocacao.buscarTodas", Alocacao.class);
        return query.getResultList();
    }

    public List<Alocacao> buscarPeloStatus(StatusAlocacao status) {

        EntityManager em = getEntityManager();
        TypedQuery<Alocacao> query = em.createNamedQuery("Alocacao.buscarPeloStatus", Alocacao.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    public List<Alocacao> buscarPeloDesenvolvedor(Desenvolvedor desenvolvedor) {

        EntityManager em = getEntityManager();
        TypedQuery<Alocacao> query = em.createNamedQuery("Alocacao.buscarPeloDesenvolvedor", Alocacao.class);
        query.setParameter("desenvolvedor", desenvolvedor);
        return query.getResultList();
    }

    public boolean validarSituacaoDesenvolvedor(Desenvolvedor desenvolvedor, StatusAlocacao status) throws Exception {

        EntityManager em = getEntityManager();
        TypedQuery<Alocacao> query = em.createNamedQuery("Alocacao.validarSituacaoDesenvolvedor", Alocacao.class);
        query.setParameter("desenvolvedor", desenvolvedor);
        query.setParameter("status", status);
        if (query.getResultList().isEmpty()) {
            return true;
        } else {
            throw new Exception("O Desenvolvedor ja esta em outro projeto.");
        }
    }

    public void finalizar(Alocacao alocacao) throws Exception {
        EntityManager em = getEntityManager();
        try {
            alocacao.AlteraStatus(StatusAlocacao.Finalizada);
            em.merge(alocacao);
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (IllegalStateException e) {
                throw new Exception(ex.getMessage());
            }
        }
    }

    @Override
    public void inserir(Alocacao alocacao) throws Exception {
        EntityManager em = getEntityManager();
        try {
            validarSituacaoDesenvolvedor(alocacao.getDesenvolvedor(), StatusAlocacao.Ativa);
            em.persist(alocacao);

        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (IllegalStateException e) {
                throw new Exception(ex.getMessage());
            }
        }
    }
}
