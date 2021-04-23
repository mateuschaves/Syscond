package dao;

import exceptions.fornecedor.FornecedorJaExistente;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import pojos.Fornecedor;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class FornecedorDAO implements FornecedorDaoInterface {
    /**
     *
     * @param fornecedor utilizado para adicionar um fornecedor;
     * @throws FornecedorJaExistente caso o usuario tente adicionar um fornecedor que ja existe no BD.
     */
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

    /**
     *
     * @param fornecedor utilizado para alterar um fornecedor;
     * @throws FornecedorNaoEncontrado caso o usuario tente alterar um fornecedor que nao existe no BD.
     */
    @Override
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
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

    /**
     *
     * @return retorna uma lista de fornecedores;
     * @throws FornecedorNaoEncontrado caso a lista esteja vazia.
     */
    @Override
    public List<Fornecedor> listar() throws FornecedorNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em
                    .createNamedQuery("Fornecedor.buscaTodos", Fornecedor.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new FornecedorNaoEncontrado("");
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param cnpj utilizado para procurar um fornecedor;
     * @return retorna um fornecedor;
     * @throws FornecedorNaoEncontrado caso um usuario queira procurar um fornecedor que nao existe no BD.
     */
    @Override
    public Fornecedor procurar(String cnpj) throws FornecedorNaoEncontrado{
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em
                    .createNamedQuery("Fornecedor.buscaPorCNPJ", Fornecedor.class)
                    .setParameter("cnpj", cnpj)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new FornecedorNaoEncontrado(cnpj);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param fornecedor utilizado para apagar um fornecedor;
     * @throws FornecedorNaoEncontrado caso o usuario tente deletar um fornecedor que nao existe no BD.
     */
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
