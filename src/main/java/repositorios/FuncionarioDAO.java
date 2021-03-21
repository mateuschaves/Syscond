package repositorios;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import dao.JPAUtil;
import exceptions.funcionario.FuncionarioJaExistente;
import exceptions.funcionario.FuncionarioNaoEncontrado;
import pojos.Funcionario;

public class FuncionarioDAO implements FuncionarioDaoInterface {

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

    @Override
    public void alterar(Funcionario funcionario) throws FuncionarioNaoEncontrado, FuncionarioJaExistente {
        remover(funcionario.getCpf());
        adicionar(funcionario);
    }

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

    public void remover(String cpf) throws FuncionarioNaoEncontrado{
        remover(procurar(cpf));
    }

}