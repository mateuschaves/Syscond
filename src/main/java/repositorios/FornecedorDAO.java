package repositorios;

import dao.JPAUtil;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import pojos.Fornecedor;

import javax.persistence.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;

public class FornecedorDAO implements FornecedorDaoInterface {

    @Override
    public Fornecedor procurar(String id) throws FornecedorNaoEncontrado {
        return null;
    }

    @Override
    public Fornecedor procurar(Fornecedor fornecedor) throws FornecedorNaoEncontrado {
        return null;

    }

    @Override
    public void adicionar(Fornecedor fornecedor) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(fornecedor);
            tx.commit();
        }catch (Exception a){

            System.err.println(a.getMessage());
        }


    }

    @Override
    public void remover(Fornecedor fornecedor) {

    }

    @Override
    public List<Fornecedor> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("select a from Fornecedor a",Fornecedor.class);

        List<Fornecedor> lista = query.getResultList();
        return lista;
    }

    @Override
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado {

    }
}
