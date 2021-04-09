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

        try{
            tx.begin();
            this.procurar(morador.getCpf()).getNome();
            em.remove(morador);
            tx.commit();

        }catch (Exception e){

            System.err.println("ERRO: " + e.getMessage());
            System.err.println("Não foi possivel alterar os dados," +
                    " pois o objeto alvo não existe no banco de dados");
        }finally {
            em.close();
        }

    }

    @Override
    public List<Morador> listar() {

        EntityManager em = JPAUtil.getEntityManager();

        try{
            return (List<Morador>) em.createQuery("select a from Morador a", Morador.class).getResultList();
        }catch (Exception e){

            e.getMessage();


        }finally {
            em.close();
        }

        return (List<Morador>) null;
    }

    @Override
    public void alterar(Morador morador){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            this.procurar(morador.getCpf()).getCpf();
            em.merge(morador);
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

}
