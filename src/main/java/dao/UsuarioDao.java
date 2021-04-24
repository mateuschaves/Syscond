package dao;

import exceptions.fornecedor.FornecedorNaoEncontrado;
import pojos.Apartamento;
import pojos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements UsuarioDaoInterface{
    @Override
    public Usuario procurar(String login) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Usuario retorno = null;
        try{
            tx.begin();
            retorno = em.find(Usuario.class,login);
            tx.commit();

        }catch (Exception e){
            System.err.println("ERRO: " + e.getMessage());
            System.out.println("Não foi possivel encontrar o Usuário: " + login);
        }finally {
            em.close();
        }
        return retorno;
    }

    @Override
    public Usuario procurar(Usuario usuario) {

        return(this.procurar(usuario.getLogin()));

    }

    @Override
    public void adicionar(Usuario usuario) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(usuario);
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

    @Override
    public void remover(Usuario usuario){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            procurar(usuario).getLogin();
            tx.begin();
            usuario = em.merge(usuario);
            em.remove(usuario);
            tx.commit();

        }catch (Exception e){

            e.getMessage();

        }finally {

            em.close();
        }
    }

    @Override
    public List<Usuario> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        List<Usuario> lista = new ArrayList<>();

        try{
            Query query = em.createQuery("select a from Usuario a",Usuario.class);
            lista = query.getResultList();

        }catch (Exception e){
            e.getMessage();
        }finally {
            em.close();
        }
        return lista;
    }

    @Override
    public void alterar(Usuario usuario) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            procurar(usuario).getLogin();
            tx.begin();
            this.procurar(usuario).getLogin();
            em.merge(usuario);
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
