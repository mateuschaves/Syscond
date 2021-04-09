package dao;

import exceptions.fornecedor.FornecedorJaExistente;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import pojos.Fornecedor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO implements FornecedorDaoInterface {

    @Override
    public void adicionar(Fornecedor fornecedor) throws FornecedorJaExistente {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(fornecedor);
        }catch (EntityExistsException  a){
            throw new FornecedorJaExistente(fornecedor.getCnpj());
        }finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            this.procurar(fornecedor.getCnpj()).getNome();
            em.merge(fornecedor);
            tx.commit();
        }
        catch(IllegalArgumentException  e){

            System.err.println("ERRO: " + e.getMessage());
            System.err.println("Não foi possivel alterar os dados," +
                    " pois o objeto alvo não existe no banco de dados");

        }finally {

            em.close();
        }
    }

    @Override
    public List<Fornecedor> listar() throws FornecedorNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (ArrayList<Fornecedor>) em
                    .createNamedQuery("Fornecedor.buscaTodos", Fornecedor.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new FornecedorNaoEncontrado("");
        } finally {
            em.close();
        }
    }

    @Override
    public Fornecedor procurar(String cnpj) throws FornecedorNaoEncontrado{
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (Fornecedor) em
                    .createNamedQuery("Fornecedor.buscaPorCNPJ", Fornecedor.class)
                    .setParameter("cnpj", cnpj)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new FornecedorNaoEncontrado(cnpj);
        } finally {
            em.close();
        }
    }

    @Override
    public void remover(Fornecedor fornecedor) throws FornecedorNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(em.merge(fornecedor));
        }catch (IllegalArgumentException e){
            throw new FornecedorNaoEncontrado(fornecedor.getCnpj());
        }finally {
            em.getTransaction().commit();
            em.close();
        }
    }
    
}
