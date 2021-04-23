import dao.ApartamentoDAO;
import dao.JPAUtil;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojos.Apartamento;

import javax.persistence.EntityManager;
import java.util.List;

public class ApartamentoDAOTest {


    ApartamentoDAO apartamentoDAO = new ApartamentoDAO();

    @Before
    public void cleanUp() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("delete from apartamento").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }


    @Test
    public void shouldBeAbleToCreateApartmento() {
        Apartamento apartamentoMock = new Apartamento();
        apartamentoMock.setAndar("1");
        apartamentoMock.setBloco("A");
        apartamentoMock.setNumero("1");

        try {
            this.apartamentoDAO.adicionar(apartamentoMock);
            List<Apartamento> apartamentos = this.apartamentoDAO.listar();
            Assert.assertNotNull(apartamentos);
            Assert.assertEquals(1, apartamentos.size());

            Apartamento apartamentoCreated = this.apartamentoDAO.procurar(apartamentoMock.getNumero());

            Assert.assertNotNull(apartamentoCreated);
            Assert.assertEquals(apartamentoMock.getNumero(), apartamentoCreated.getNumero());
            Assert.assertEquals(apartamentoMock.getAndar(), apartamentoCreated.getAndar());
            Assert.assertEquals(apartamentoMock.getBloco(), apartamentoCreated.getBloco());
        } catch (Exception e) {}
    }

    @Test
    public void shouldBeAbleToRemoveApartamento() {
        Apartamento apartamentoMock = new Apartamento();
        apartamentoMock.setAndar("1");
        apartamentoMock.setBloco("A");
        apartamentoMock.setNumero("1");

        try {
            this.apartamentoDAO.adicionar(apartamentoMock);

            this.apartamentoDAO.remover(apartamentoMock);

            List<Apartamento> apartamentosAfterRemove = this.apartamentoDAO.listar();
            Assert.assertNotNull(apartamentosAfterRemove);
            Assert.assertEquals(0, apartamentosAfterRemove.size());

        } catch (Exception e) {}
    }

    @Test
    public void shouldBeAbleToUpdateApartamento() {
        Apartamento apartamentoMock = new Apartamento();
        apartamentoMock.setAndar("1");
        apartamentoMock.setBloco("A");
        apartamentoMock.setNumero("1");

        try {
            this.apartamentoDAO.adicionar(apartamentoMock);

            Apartamento apartamentoCreated = this.apartamentoDAO.procurar(apartamentoMock.getNumero());

            apartamentoCreated.setAndar("2");
            apartamentoCreated.setBloco("B");

            this.apartamentoDAO.alterar(apartamentoCreated);

            Apartamento apartamentoUpdated = this.apartamentoDAO.procurar(apartamentoMock.getNumero());

            Assert.assertNotNull(apartamentoUpdated);
            Assert.assertEquals(apartamentoCreated.getBloco(), apartamentoUpdated.getBloco());
            Assert.assertEquals(apartamentoCreated.getAndar(), apartamentoUpdated.getAndar());
        } catch (Exception e) {}
    }
}
