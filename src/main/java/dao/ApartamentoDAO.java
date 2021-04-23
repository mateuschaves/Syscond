package dao;

import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import javassist.bytecode.stackmap.BasicBlock;
import org.hibernate.exception.ConstraintViolationException;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;


import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ApartamentoDAO implements ApartamentoDaoInterface{

    /**
     *
     * @param apartamento utilizado para adicionar um apartamento;
     * @throws ApartamentoJaExistente caso o usuario tente adicionar um apartamento que ja existe.
     */
    @Override
    public void adicionar(Apartamento apartamento) throws ApartamentoJaExistente, RollbackException {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(apartamento);
            tx.commit();
        }catch (Exception e) {
            //e.printStackTrace();
            String exceptionName = e.getCause().getClass().getName();

            if(exceptionName.equals("javax.validation.ConstraintViolationException")){
                System.out.println("Erro: Erro na entrada dos dados => " + exceptionName);
                System.out.println("Message: " + e.getCause().getMessage());
            }else{
                System.out.println("Erro:" + exceptionName);
                System.out.println("Message: " + e.getMessage());
            }

        }
        finally {
            em.close();
        }
    }

    /**
     *
     * @param apartamento utilizado para alterar um apartamento;
     * @throws ApartamentoNaoEncontrado caso um usuario queira alterar um apartamento que nao existe;
     * @throws ApartamentoJaExistente confere se o apartamento existe, pois nao eh possivel alterar algo que
     * nao existe;
     */
    @Override
    public void alterar(Apartamento apartamento) throws ApartamentoNaoEncontrado, ApartamentoJaExistente {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            procurar(apartamento).getNumero();
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

    /**
     *
     * @return retorna uma lista de apartamentos;
     * @throws ApartamentoNaoEncontrado caso a lista esteja vazia.
     */
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

    /**
     * @param numero procura o apartamento pelo seu numero;
     * @return retorna um apartamento;
     * @throws ApartamentoNaoEncontrado caso um usuario queria procurar um apartamento que nao existe.
     */
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
            System.out.println("Não foi possivel encontrar o apartamento: " + numero);

        }finally {
            em.close();
        }
        return retorno;
    }
    /**
     * @param apartamento procura o apartamento pelo seu numero;
     * @return retorna um apartamento;
     * @throws ApartamentoNaoEncontrado caso um usuario queria procurar um apartamento que nao existe.
     */
    private Apartamento procurar(Apartamento apartamento){
        Apartamento returned = null;
        try {
            returned = procurar(apartamento.getNumero());
        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("Não foi possivel encontrar o apartamento: " + apartamento.getNumero());
        }
        return returned;
    }

    /**
     *
     * @param apartamento caso um usuario queira apagar um apartamento;
     * @throws ApartamentoNaoEncontrado caso um usuario queira deletar um apartamento que nao existe.
     */
    @Override
    public void remover(Apartamento apartamento) throws ApartamentoNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            procurar(apartamento).getNumero();
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
}
