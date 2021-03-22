package repositorios;

import dao.JPAUtil;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import pojos.Carro;
import pojos.Fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CarroDAO implements CarroDaoInterface{

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

    @Override
    public void adicionar(Carro carro) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(carro);
            tx.commit();
        }catch (Exception a){
            //a.printStackTrace();
            System.err.println("Id já existente: " + a.getMessage());
        }finally {
            em.close();
        }

    }

    @Override
    public void remover(Carro carro) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        carro = em.merge(carro);
        em.remove(carro);
        tx.commit();
        em.close();

    }

    @Override
    public List<Carro> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("select a from Carro a", Carro.class);

        List<Carro> lista = query.getResultList();
        return lista;
    }

    @Override
    public void alterar(Carro carro){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            Carro exemplo = this.procurar(carro.getPlaca());
            exemplo.setPlaca(carro.getPlaca());
            exemplo.setModelo(carro.getModelo());
            exemplo.setCor(carro.getCor());
            exemplo.setProprietario(carro.getProprietario());

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
