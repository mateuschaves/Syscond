package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import exceptions.funcionario.FuncionarioJaExistente;
import exceptions.funcionario.FuncionarioNaoEncontrado;
import pojos.Apartamento;
import pojos.Funcionario;

/**
 * @author grupo Sith; Implementando a interface de funcionario sobrescrevendo suas assinaturas
 */
public class FuncionarioDAO implements FuncionarioDaoInterface {

    /**
     *
     * @param funcionario utilizado para adicionar um funcionario;
     * @throws FuncionarioJaExistente caso um usuario queira adicionar um funcionario que ja existe no
     * banco de dados.
     */
    @Override
    public void adicionar(Funcionario funcionario) throws FuncionarioJaExistente {
        try {
            procurar(funcionario.getCpf());
            throw new FuncionarioJaExistente(funcionario.getCpf());
        } catch (FuncionarioNaoEncontrado e) {
            EntityManager em = JPAUtil.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(funcionario);
            tx.commit();
            em.close();
        }
    }

    /**
     *
     * @param funcionario utilizado para alterar um funcionario;
     * @throws FuncionarioNaoEncontrado caso um usuario queira alterar um funcionario que nao existe BD;
     * @throws FuncionarioJaExistente confere se o funcionario existe no BD,pois nao eh possivel alterar algo que
     * nao existe.
     */
    @Override
    public void alterar(Funcionario funcionario) throws FuncionarioNaoEncontrado, FuncionarioJaExistente {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            this.procurar(funcionario.getCpf()).getCpf();
            em.merge(funcionario);
            tx.commit();
        }
        catch(Exception e){
            System.err.println("ERRO: " + e.getMessage());
            System.err.println("Não foi possivel alterar os dados," +
                    " pois o objeto alvo não existe no banco de dados");
        }finally {
            em.close();
        }

    }

    /**
     *
     * @return retorna uma ArrayList de funcionarios;
     * @throws FuncionarioNaoEncontrado caso o Arraylist esteja vazio.
     */
    @Override
    public ArrayList<Funcionario> listar() throws FuncionarioNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (ArrayList<Funcionario>) em.createNamedQuery("Funcionario.buscaTodos", Funcionario.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new FuncionarioNaoEncontrado("");
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param cpf utilizado para procurar um funcionario;
     * @return retorna um funcionario;
     * @throws FuncionarioNaoEncontrado caso um usuario queira procurar um funcionario que nao existe BD.
     */
    @Override
    public Funcionario procurar(String cpf) throws FuncionarioNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (Funcionario) em.createNamedQuery("Funcionario.buscaPorCPF", Funcionario.class)
                    .setParameter("cpf", cpf).getSingleResult();
        } catch (NoResultException e) {
            throw new FuncionarioNaoEncontrado(cpf);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param funcionario utilizado para procurar um funcionario;
     * @return retorna um funcionario
     */
    public Funcionario procurar(Funcionario funcionario){
        Funcionario returned = null;
        try {
            returned = procurar(funcionario.getCpf());
        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Não foi possivel encontrar o apartamento: " + funcionario.getCpf());
        }
        return returned;
    }

    /**
     *
     * @param funcionario utilizado para apagar um funcionario;
     * @throws FuncionarioNaoEncontrado caso um usuario queira deletar um funcionario que nao existe BD.
     */
    @Override
    public void remover(Funcionario funcionario) throws FuncionarioNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(
            (em.contains(funcionario)) ? funcionario : em.merge(funcionario)
        );
        em.getTransaction().commit();
        em.close();
    }

    /**
     *
     * @param cpf outra forma de deletar um funcionario;
     * @throws FuncionarioNaoEncontrado caso um usuario queira deletar um funcionario que nao existe BD.
     */
    public void remover(String cpf) throws FuncionarioNaoEncontrado{
        remover(procurar(cpf));
    }

}