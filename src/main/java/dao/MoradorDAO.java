package dao;

import exceptions.carro.CarroJaExistente;
import exceptions.carro.CarroNaoEncontrado;
import exceptions.morador.MoradorJaExistente;
import exceptions.morador.MoradorNaoEncontrado;
import pojos.Morador;

import javax.persistence.*;
import java.util.List;

public class MoradorDAO implements MoradorDaoInterface{

    @Override
    public Morador procurar(String cpf) throws MoradorNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Morador retorno = null;
        try {
            tx.begin();
            retorno = em.find(Morador.class,cpf);
            tx.commit();

        }catch(Exception e){
            throw new MoradorNaoEncontrado(cpf);

        }finally {
            em.close();
        }
        return retorno;
    }

    @Override
    public void adicionar(Morador morador) throws MoradorJaExistente {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(morador);
            tx.commit();
        }catch (EntityExistsException a){
            throw new MoradorJaExistente(morador.getCpf());
        }finally {
            em.close();
        }

    }

    @Override
    public void remover(Morador morador) throws MoradorNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            this.procurar(morador.getCpf()).getNome();
            em.remove(morador);
            tx.commit();

        }catch (MoradorNaoEncontrado e){
            throw new MoradorNaoEncontrado(morador.getCpf());
        }finally {
            em.close();
        }

    }

    @Override
    public List<Morador> listar() throws MoradorNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();

        try{
            return (List<Morador>) em.createQuery("select a from Morador a", Morador.class).getResultList();
        }catch (Exception e){
            throw new MoradorNaoEncontrado();
        }finally {
            em.close();
        }
    }

    @Override
    public void alterar(Morador morador) throws MoradorNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            this.procurar(morador.getCpf()).getCpf();
            em.merge(morador);
            tx.commit();
        }
        catch(MoradorNaoEncontrado | NullPointerException e){
            throw new MoradorNaoEncontrado(morador.getCpf());

        }finally {
            em.close();
        }
    }

}
