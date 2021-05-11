package dao;

import pojos.Morador;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 *  Implementando a interface de morador sobrescrevendo suas assinaturas
 */
public class MoradorDAO implements MoradorDaoInterface{
    /**
     *
     * @param cpf utilizado para procurar um morador;
     * @return retorna uma morador.
     */
    @Override
    public Morador procurar(String cpf){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Morador retorno = null;
        try {
            tx.begin();
            retorno = em.find(Morador.class,cpf);
            System.out.println("Retorno: " + retorno.getNome());
            tx.commit();

        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Não foi possivel encontrar os dados.");

        }finally {
            em.close();
        }
        return retorno;
    }

    /**
     *
     * @param morador utilizado para procurar um morador;
     * @return retorna uma morador.
     */
    public Morador procurar(Morador morador){
        return(this.procurar(morador.getCpf()));
    }

    /**
     *
     * @param morador utilizado para inserir um morador.
     */
    @Override
    public void adicionar(Morador morador) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(morador);
            tx.commit();
        }catch (Exception e){
            //e.printStackTrace();
            String exceptionName = e.getCause().getClass().getName();

            if(exceptionName.equals("javax.validation.ConstraintViolationException")){
                System.out.println("Erro: Erro na entrada dos dados => " + exceptionName);
                System.out.println("Message: " + e.getCause().getMessage());
            }else{
                System.out.println("Erro:" + exceptionName);
                System.out.println("Message: " + e.getMessage());
            }
        }finally {
            em.close();
        }

    }

    /**
     *
     * @param morador utilizado para apagar um morador.
     */
    @Override
    public void remover(Morador morador) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            //procurar(morador.getCpf()).getCpf();
            tx.begin();
            morador = em.merge(morador);
            em.remove(morador);
            tx.commit();

        }catch (Exception e){

            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Não foi possivel apagar os dados," +
                    " pois o objeto alvo não existe no banco de dados");
        }finally {
            em.close();
        }

    }
    /**
     *
     * @return retorna uma lista de moradores.
     */
    @Override
    public List<Morador> listar() {

        EntityManager em = JPAUtil.getEntityManager();

        try{
            return (List<Morador>) em.createQuery("select a from Morador a", Morador.class).getResultList();
        }catch (Exception e){

            e.getMessage();
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("ERRO: Não foi possivel Listar os Moradores" );

        }finally {
            em.close();
        }

        return (List<Morador>) null;
    }

    /**
     *
     * @param morador utilizado para alterar um morador.
     */
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

            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Não foi possivel alterar os dados," +
                    " pois o objeto alvo não existe no banco de dados");

        }finally {
            em.close();
        }
    }

    public void cleanUp(){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0;").executeUpdate();
            em.createNativeQuery("truncate table morador;").executeUpdate();
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
