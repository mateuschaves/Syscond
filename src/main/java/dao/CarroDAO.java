package dao;

import pojos.Apartamento;
import pojos.Carro;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CarroDAO implements CarroDaoInterface{

    /**
     *
     * @param placa utilizado para procurar um carro;
     * @return retorna um carro.
     */
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

    /**
     * @param carro utilizado para adicionar um carro.
     */
    @Override
    public void adicionar(Carro carro) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(carro);
            tx.commit();
        }catch (Exception a){
             a.printStackTrace();
            System.out.println("Id já existente: " + a.getCause().getMessage());
        }finally {
            em.close();
        }

    }

    /**
     *
     * @param carro utilizado para remover um carro.
     */
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

    /**
     * @return retorna uma lista de carros.
     */
    @Override
    public List<Carro> listar() {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return (List<Carro>) em.createQuery("select a from Carro a", Carro.class)
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERRO" + e.getMessage());
        }finally {
            em.close();
        }

        return (List<Carro>) null;
    }

    /**
     *
     * @param carro utilizado para alterar um carro.
     */
    @Override
    public void alterar(Carro carro){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            this.procurar(carro.getPlaca()).getPlaca();
            em.merge(carro);
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

    /**
     * @param carro procura o carro por um ojeto carro;
     * @return retorna um carro;
     */
    @Override
    public Carro procurar(Carro carro){
        Carro returned = null;
        try{
            returned = procurar(carro.getPlaca());
        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Não foi possivel encontrar o seu carro: " + carro.getPlaca());
        }
        return returned;
    }


}
