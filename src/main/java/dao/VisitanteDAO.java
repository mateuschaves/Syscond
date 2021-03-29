package dao;


import exceptions.visitante.VisitanteJaExistente;
import exceptions.visitante.VisitanteNaoEncontrado;
import pojos.Carro;
import pojos.Visitante;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class VisitanteDAO implements VisitanteDaoInterface{


    @Override
    public Visitante procurar(String cpf) throws VisitanteNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Visitante retorno = null;
        try {
            tx.begin();
            retorno = em.find(Visitante.class,cpf);
            tx.commit();

        }catch(Exception e){
            throw new VisitanteNaoEncontrado(cpf);

        }finally {
            em.close();
        }
        return retorno;

    }

    @Override
    public void adicionar(Visitante visitante) throws VisitanteJaExistente {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(visitante);
            tx.commit();
        }catch (EntityExistsException a){
            throw new VisitanteJaExistente(visitante.getCpf());
        }finally {
            em.close();
        }

    }

    @Override
    public void remover(Visitante visitante) throws VisitanteNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            visitante = em.merge(visitante);
            em.remove(visitante);
            tx.commit();
        }catch (Exception e){
            throw new VisitanteNaoEncontrado(visitante.getCpf());
        }finally {
            em.close();
        }
    }

    @Override
    public List<Visitante> listar() throws VisitanteNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (List<Visitante>) em.createQuery("select a from Visitante a", Visitante.class)
                    .getResultList();
        }catch (Exception e){
            throw new VisitanteNaoEncontrado();
        }finally {
            em.close();
        }
    }

    @Override
    public void alterar(Visitante visitante) throws VisitanteNaoEncontrado {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{

            tx.begin();
            this.procurar(visitante.getCpf()).getCpf();
            em.merge(visitante);
            tx.commit();
        }
        catch(NullPointerException | VisitanteNaoEncontrado e){
            throw new VisitanteNaoEncontrado(visitante.getCpf());
        }finally {
            em.close();

        }
    }
}
