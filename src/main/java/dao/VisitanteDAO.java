package dao;


import pojos.Carro;
import pojos.Visitante;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author grupo Sith; Implementando a interface de visitante sobrescrevendo suas assinaturas
 */
public class VisitanteDAO implements VisitanteDaoInterface{

    /**
     *
     * @param cpf utilizado para procurar um visitante;
     * @return retorna um visitante.
     */
    @Override
    public Visitante procurar(String cpf) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Visitante retorno = null;
        try {
            tx.begin();
            retorno = em.find(Visitante.class,cpf);
            tx.commit();

        }catch(NoResultException e){

            System.err.println("ERRO: " + e.getMessage());

        }finally {
            em.close();
        }
        return retorno;

    }

    /**
     *
     * @param visitante utilizado para adicionar um visitante.
     */
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

    /**
     *
     * @param visitante utilizado para deletar um visitante.
     */
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

    /**
     *
     * @return retorna uma lista de visitantes.
     */
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

    /**
     *
     * @param visitante utilizado para alterar um visitante.
     */
    @Override
    public void alterar(Visitante visitante) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{

            tx.begin();
            this.procurar(visitante.getCpf()).getCpf();
            em.merge(visitante);
            tx.commit();
        }
        catch(NullPointerException e){

            System.err.println("ERRO: " + e.getMessage());
            System.err.println("Não foi possivel alterar os dados," +
                    " pois o objeto alvo não existe no banco de dados");
        }finally {
            em.close();

        }
    }
    @Override
    public Visitante procurar(Visitante visitante){
        Visitante returned = null;
        try{
            returned = procurar(visitante.getCpf());
        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Não foi possivel encontrar o seu visitante: " + visitante.getNome());
        }
        return returned;
    }


}
