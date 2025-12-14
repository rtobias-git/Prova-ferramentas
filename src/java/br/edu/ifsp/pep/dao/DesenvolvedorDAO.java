package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entidade.Desenvolvedor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DesenvolvedorDAO extends AbstractDAO<Desenvolvedor> {

    public Desenvolvedor buscarPeloId(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Desenvolvedor.class, id);
    }

    public List<Desenvolvedor> buscarTodos() {
        EntityManager em = getEntityManager();
        TypedQuery<Desenvolvedor> query = em.createNamedQuery("Desenvolvedor.buscarTodos", Desenvolvedor.class);
        return query.getResultList();
    }
}
