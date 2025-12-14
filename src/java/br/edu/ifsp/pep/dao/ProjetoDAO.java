package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entidade.Projeto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ProjetoDAO extends AbstractDAO<Projeto> {

    public Projeto buscarPeloId(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Projeto.class, id);
    }

    public List<Projeto> buscarTodos() {
        EntityManager em = getEntityManager();
        TypedQuery<Projeto> query = em.createNamedQuery("Projeto.buscarTodos", Projeto.class);
        return query.getResultList();
    }
}
