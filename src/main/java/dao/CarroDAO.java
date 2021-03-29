package dao;

import exceptions.carro.CarroJaExistente;
import exceptions.carro.CarroNaoEncontrado;
import pojos.Carro;

import javax.persistence.*;
import java.util.List;

public class CarroDAO implements CarroDaoInterface{

    @Override
    public Carro procurar(String placa) throws CarroNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Carro retorno = null;
        try {
            tx.begin();
            retorno = em.find(Carro.class,placa);
            tx.commit();

        }catch(Exception e){
            throw new CarroNaoEncontrado(placa);
        }finally {
            em.close();
        }
        return retorno;
    }

    @Override
    public void adicionar(Carro carro) throws CarroJaExistente {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(carro);
            tx.commit();
        }catch (EntityExistsException a){
            throw new CarroJaExistente(carro.getPlaca());
        }finally {
            em.close();
        }

    }

    @Override
    public void remover(Carro carro) throws CarroNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            carro = em.merge(carro);
            em.remove(carro);
            tx.commit();
        }catch (Exception e){
            throw new CarroNaoEncontrado(carro.getPlaca());
        }finally {
            em.close();
        }



    }

    @Override
    public List<Carro> listar() throws CarroNaoEncontrado {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (List<Carro>) em.createQuery("select a from Carro a", Carro.class)
                    .getResultList();
        }catch (Exception e){
            throw new CarroNaoEncontrado();
        }finally {
            em.close();
        }
    }

    @Override
    public void alterar(Carro carro) throws CarroNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            this.procurar(carro.getPlaca()).getPlaca();
            em.merge(carro);
            tx.commit();
        }
        catch(CarroNaoEncontrado | NullPointerException e){
            throw new CarroNaoEncontrado(carro.getPlaca());
        }finally {
            em.close();

        }
    }
}
