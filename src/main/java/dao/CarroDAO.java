package dao;

import pojos.Carro;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CarroDAO implements CarroDaoInterface{

    @Override
    public Carro procurar(String placa){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Carro retorno = null;
        try {
            tx.begin();
            retorno = em.find(Carro.class,placa);
            tx.commit();

        }catch(NullPointerException e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();
        }
        return retorno;
    }

    @Override
    public void adicionar(Carro carro) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(carro);
            tx.commit();
        }catch (Exception a){
            //a.printStackTrace();
            System.err.println("Id já existente: " + a.getMessage());
        }finally {
            em.close();
        }

    }

    @Override
    public void remover(Carro carro) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            carro = em.merge(carro);
            em.remove(carro);
            tx.commit();
        }catch (Exception e){
            e.getMessage();
        }finally {
            em.close();
        }



    }

    @Override
    public List<Carro> listar() {

        EntityManager em = JPAUtil.getEntityManager();


        try {
            return (List<Carro>) em.createQuery("select a from Carro a", Carro.class)
                    .getResultList();
        }catch (Exception e){
            e.getMessage();
        }finally {
            em.close();
        }

        return (List<Carro>) null;
    }

    @Override
    public void alterar(Carro carro){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            em.merge(carro);
            tx.commit();
        }
        catch(Exception e){

            System.err.println("ERRO: " + e.getMessage());
        }finally {
            em.close();

        }
    }
}
