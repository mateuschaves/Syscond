package dao;

import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import pojos.Apartamento;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO implements ApartamentoDaoInterface{


    @Override
    public void adicionar(Apartamento apartamento) throws ApartamentoJaExistente {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(apartamento);
            tx.commit();
        }catch (Exception a){
            //a.printStackTrace();
            System.err.println("Id já existente: " + a.getMessage());
        }finally {
            em.close();
        }
    }

    @Override
    public void alterar(Apartamento apartamento) throws ApartamentoNaoEncontrado, ApartamentoJaExistente {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            this.procurar(apartamento.getNumero()).getNumero();
            em.merge(apartamento);
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

    @Override
    public List<Apartamento> listar() throws ApartamentoNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        List<Apartamento> lista = new ArrayList<>();

        try{

            Query query = em.createQuery("select a from Apartamento a",Apartamento.class);
            lista = query.getResultList();

        }catch (Exception e){
            e.getMessage();
        }finally {
            em.close();
        }
        return lista;
    }

    @Override
    public Apartamento procurar(String numero) throws ApartamentoNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Apartamento retorno = new Apartamento();

        try {
            tx.begin();
            retorno = em.find(Apartamento.class,numero);
            tx.commit();

        }catch(NullPointerException e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();
        }
        return retorno;
    }

    @Override
    public void remover(Apartamento apartamento) throws ApartamentoNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            apartamento = em.merge(apartamento);
            em.remove(apartamento);
            tx.commit();

        }catch (Exception e){
            e.getMessage();
        }finally {

            em.close();

        }

    }

    public void cleanUp() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0;").executeUpdate();
            em.createNativeQuery("truncate table apartamento;").executeUpdate();
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1;").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
