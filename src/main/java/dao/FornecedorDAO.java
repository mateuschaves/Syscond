package dao;

import exceptions.fornecedor.FornecedorJaExistente;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import pojos.Fornecedor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO implements FornecedorDaoInterface {

    @Override
    public Fornecedor procurar(String cnpj){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Fornecedor retorno = null;
        try {
            tx.begin();
            retorno = em.find(Fornecedor.class,cnpj);
            tx.commit();

        }catch(NullPointerException e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();
        }
        return retorno;
    }

    @Override
    public void adicionar(Fornecedor fornecedor) throws FornecedorJaExistente {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(fornecedor);
        }catch (EntityExistsException  a){
            throw new FornecedorJaExistente(fornecedor.getCnpj());
        }finally {
            tx.commit();
            em.close();
        }

    }

    @Override
    public void remover(Fornecedor fornecedor) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            fornecedor = em.merge(fornecedor);
            em.remove(fornecedor);
            tx.commit();
        }catch (Exception e){
            e.getMessage();
        }finally {
            em.close();
        }
    }

    @Override
    public List<Fornecedor> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("select a from Fornecedor a", Fornecedor.class);

        List<Fornecedor> lista = new ArrayList<>();

        try {
            lista = query.getResultList();
        }catch (Exception e){
            e.getMessage();
        }finally {
            em.close();
        }

        return lista;
    }

    @Override
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.merge(fornecedor);
            tx.commit();
        }
        catch(Exception e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();

        }
    }
}
