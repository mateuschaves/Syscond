import dao.FornecedorDAO;
import dao.JPAUtil;
import exceptions.fornecedor.FornecedorJaExistente;
import exceptions.fornecedor.FornecedorNaoEncontrado;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojos.Fornecedor;

import javax.persistence.EntityManager;
import java.util.List;

public class FornecedorTest {

    private final Fornecedor fornecedorMock = new Fornecedor("02.117.937/0002-58", "Uci Ribeiro " +
            "LTDA", "32 9 3215-3112");

    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();

    @Before
    public void cleanUp() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("delete from fornecedor;").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void shouldCreateFornecedor() throws FornecedorJaExistente, FornecedorNaoEncontrado {
        this.fornecedorDAO.adicionar(fornecedorMock);
        Assert.assertNotNull(fornecedorDAO.listar().get(0));
    }

    @Test
    public void shouldListZeroFornecedorWhenNoOneWasCreated() throws FornecedorNaoEncontrado {
        List<Fornecedor> fornecedores = fornecedorDAO.listar();
        Assert.assertNotNull(fornecedores);
        Assert.assertEquals(0, fornecedores.size());
    }

    @Test
    public void shouldListOneFornecedorWhenOneWasCreated() throws FornecedorNaoEncontrado, FornecedorJaExistente {
        this.fornecedorDAO.adicionar(fornecedorMock);
        Assert.assertNotNull(fornecedorMock);
        Assert.assertEquals(1, fornecedorDAO.listar().size());
    }

    @Test
    public void shouldBeAbleToUpdateFornecedor() throws FornecedorJaExistente, FornecedorNaoEncontrado {
        this.fornecedorDAO.adicionar(fornecedorMock);
        fornecedorMock.setNome("Fornecedor Editado");
        this.fornecedorDAO.alterar(fornecedorMock);
        Fornecedor fornecedorUpdated = this.fornecedorDAO.procurar(fornecedorMock.getCnpj());
        Assert.assertNotNull(fornecedorUpdated);
        Assert.assertEquals(fornecedorMock.getNome(), fornecedorUpdated.getNome());
    }
}
