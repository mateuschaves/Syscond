package dao;


import pojos.Carro;
import pojos.Visitante;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class VisitanteDAO implements VisitanteDaoInterface{


    @Override
    public Visitante procurar(String cpf) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Visitante retorno = null;
        try {
            tx.begin();
            retorno = em.find(Visitante.class,cpf);
            tx.commit();

        }catch(NullPointerException e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();
        }
        return retorno;

    }

    @Override
    public void adicionar(Visitante visitante) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(visitante);
            tx.commit();
        }catch (Exception a){
            //a.printStackTrace();
            System.err.println("Id já existente: " + a.getMessage());
        }finally {
            em.close();
        }

    }

    @Override
    public void remover(Visitante visitante) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            visitante = em.merge(visitante);
            em.remove(visitante);
            tx.commit();
        }catch (Exception e){
            e.getMessage();
        }finally {
            em.close();
        }
    }

    @Override
    public List<Visitante> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (List<Visitante>) em.createQuery("select a from Visitante a", Visitante.class)
                    .getResultList();
        }catch (Exception e){
            e.getMessage();
        }finally {
            em.close();
        }

        return (List<Visitante>) null;
    }

    @Override
    public void alterar(Visitante visitante) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            em.merge(visitante);
            tx.commit();
        }
        catch(Exception e){

            System.err.println("ERRO: " + e.getMessage());
        }finally {
            em.close();

        }
    }
}
