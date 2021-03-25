package dao;

import pojos.Morador;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class MoradorDAO implements MoradorDaoInterface{

    @Override
    public Morador procurar(String cpf){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Morador retorno = null;
        try {
            tx.begin();
            retorno = em.find(Morador.class,cpf);
            tx.commit();

        }catch(NullPointerException e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();
        }
        return retorno;
    }

    @Override
    public void adicionar(Morador morador) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(morador);



            tx.commit();
        }catch (Exception a){
            //a.printStackTrace();
            System.err.println("Id já existente: " + a.getMessage());
        }finally {
            em.close();
        }

    }

    @Override
    public void remover(Morador morador) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        morador = em.merge(morador);
        em.remove(morador);
        tx.commit();
        em.close();

    }

    @Override
    public List<Morador> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("select a from Morador a", Morador.class);

        List<Morador> lista = query.getResultList();
        return lista;
    }

    @Override
    public void alterar(Morador morador){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            Morador exemplo = this.procurar(morador.getCpf());
            exemplo.setCpf(morador.getCpf());
            exemplo.setNome(morador.getNome());
            exemplo.setCarros(morador.getCarros());


            tx.begin();
            em.merge(exemplo);
            tx.commit();
        }
        catch(Exception e){

            System.err.println("ERRO: " + e.getMessage());
        }finally {
            em.close();

        }
    }

}
