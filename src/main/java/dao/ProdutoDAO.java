package dao;


import pojos.Fornecedor;
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

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Produto retorno = null;
        try {
            tx.begin();
            retorno = em.find(Produto.class,codigoDeBarras);
            tx.commit();

        }catch(NullPointerException e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();
        }
        return retorno;

    }

    @Override
    public void adicionar(Produto produto) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(produto);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        } finally {
            em.close();
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
        }catch(Exception e){
            e.getMessage();
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Produto> listar() {

        EntityManager em = JPAUtil.getEntityManager();
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
            this.procurar(produto.getCodigo()).getCodigo();
            em.merge(produto);
            tx.commit();
        }catch (Exception e){
            System.err.println("ERRO: " + e.getMessage());
            System.err.println("Não foi possivel alterar os dados," +
                    " pois o objeto alvo não existe no banco de dados");
        }finally {
            em.close();
        }
    }
}
