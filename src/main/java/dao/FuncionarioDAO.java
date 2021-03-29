package dao;

import java.util.ArrayList;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import exceptions.fornecedor.FornecedorJaExistente;
import org.hibernate.exception.DataException;

import exceptions.funcionario.FuncionarioJaExistente;
import exceptions.funcionario.FuncionarioNaoEncontrado;
import pojos.Fornecedor;
import pojos.Funcionario;

public class FuncionarioDAO implements FuncionarioDaoInterface {

    @Override
    public void adicionar(Funcionario funcionario) throws FuncionarioJaExistente {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try{
            procurar(funcionario.getCpf());
            throw new FuncionarioJaExistente(funcionario.getCpf());
        }catch (FuncionarioNaoEncontrado a){
            em.persist(funcionario);
        }finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public void alterar(Funcionario funcionario) throws FuncionarioNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Funcionario f = em.getReference(Funcionario.class, funcionario.getCpf());
        try {
            f.setCpf(funcionario.getCpf());
            f.setFuncao(funcionario.getFuncao());
            f.setNome(funcionario.getNome());
            em.merge(f);
        }catch (EntityNotFoundException e){
            throw new FuncionarioNaoEncontrado(funcionario.getCpf());
        }finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public ArrayList<Funcionario> listar() throws FuncionarioNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (ArrayList<Funcionario>) em
                    .createNamedQuery("Funcionario.buscaTodos", Funcionario.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new FuncionarioNaoEncontrado();
        } finally {
            em.close();
        }
    }

    @Override
    public Funcionario procurar(String cpf) throws FuncionarioNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (Funcionario) em
                    .createNamedQuery("Funcionario.buscaPorCPF", Funcionario.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
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
        try {
            em.remove(em.getReference(Funcionario.class, funcionario.getCpf()));
        } catch (EntityNotFoundException e) {
            throw new FuncionarioNaoEncontrado(funcionario.getCpf());
        }        
        em.getTransaction().commit();
        em.close();
    }

    public void remover(String cpf) throws FuncionarioNaoEncontrado{
        remover(new Funcionario(cpf, "", ""));
    }

}