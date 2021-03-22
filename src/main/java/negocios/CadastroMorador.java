package negocios;

import dao.JPAUtil;
import pojos.Carro;
import pojos.Morador;
import repositorios.CarroDAO;
import repositorios.CarroDaoInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CadastroMorador {


    public void cadastrarMorador(Morador morador){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        CarroDaoInterface carrosDao = new CarroDAO();

        tx.begin();
        em.persist(morador);

        for (Carro a:
                morador.getCarros()) {

            carrosDao.adicionar(a);

        }




    }
}
