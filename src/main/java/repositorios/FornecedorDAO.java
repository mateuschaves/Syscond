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
    public Fornecedor procurar(String cnpj) throws FornecedorNaoEncontrado {

        List<Fornecedor> fornecedorList = this.listar();

        try {

            for (Fornecedor a: fornecedorList) {

                if(a.getCnpj().equals(cnpj)){

                    return a;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("ERRO: " + e.getMessage());

        }

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
            //a.printStackTrace();
            System.err.println(a.getMessage());
        }finally {
            em.close();
        }


    }

    @Override
    public void remover(Fornecedor fornecedor) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        fornecedor = em.merge(fornecedor);
        em.remove(fornecedor);
        tx.commit();
        em.close();

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

        Fornecedor deletado = procurar(fornecedor.getCnpj());
        this.remover(deletado);
        this.adicionar(fornecedor);
    }
}
