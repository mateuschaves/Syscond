package dao;


import exceptions.produto.ProdutoJaExistente;
import exceptions.produto.ProdutoNaoEncontrado;
import pojos.Fornecedor;
import pojos.Produto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements ProdutoDaoInterface{


    @Override
    public Produto procurar(String codigoDeBarras) throws ProdutoNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Produto retorno = null;
        try {
            tx.begin();
            retorno = em.find(Produto.class,codigoDeBarras);
            tx.commit();

        }catch(Exception e){
            throw new ProdutoNaoEncontrado(codigoDeBarras);

        }finally {
            em.close();
        }
        return retorno;

    }

    @Override
    public void adicionar(Produto produto) throws ProdutoJaExistente {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(produto);
            tx.commit();
        } catch (EntityExistsException e){
            throw new ProdutoJaExistente(produto.getCodigo());
        } finally {
            em.close();
        }

    }

    @Override
    public void remover(Produto produto) throws ProdutoNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            produto = em.merge(produto);
            em.remove(produto);
            tx.commit();
        }catch(Exception e){
            throw new ProdutoNaoEncontrado(produto.getCodigo());
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Produto> listar() throws ProdutoNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("select a from Produto a",Produto.class);

        List<Produto> lista = new ArrayList<Produto>();
        try {
            return lista = query.getResultList();

        } catch (Exception e) {
            throw new ProdutoNaoEncontrado();
        } finally {
            em.close();
        }

    }

    @Override
    public void alterar(Produto produto) throws ProdutoNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            this.procurar(produto.getCodigo()).getCodigo();
            em.merge(produto);
            tx.commit();
        }catch (ProdutoNaoEncontrado | NullPointerException e){
            throw new ProdutoNaoEncontrado(produto.getCodigo());
        }finally {
            em.close();
        }
    }
}
