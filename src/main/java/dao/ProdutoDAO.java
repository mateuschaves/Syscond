package dao;

import exceptions.funcionario.FuncionarioNaoEncontrado;
import pojos.Funcionario;
import pojos.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements ProdutoDaoInterface{


    @Override
    public Produto procurar(String codigoDeBarras) {
        return null;
    }

    @Override
    public void adicionar(Produto produto) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(produto);
            tx.commit();
            em.close();
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }

    }

    @Override
    public void remover(Produto morador) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            morador = em.merge(morador);
            em.remove(morador);
            tx.commit();
            em.close();

        }catch(Exception e){
            e.getMessage();
        }
    }

    @Override
    public List<Produto> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Query query = em.createQuery("select a from Produto a",Produto.class);

        List<Produto> lista = new ArrayList<Produto>();

        try {
            return lista = query.getResultList();

        } catch (NoResultException e) {
            e.getMessage();
        } finally {
            em.close();
        }


        return null;
    }

    @Override
    public void alterar(Produto produto) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(produto);
            tx.commit();
            em.close();
        }catch (Exception e){
            e.getMessage();
        }
    }
}
