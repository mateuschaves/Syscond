import dao.JPAUtil;
import dao.ProdutoDAO;
import org.junit.jupiter.api.*;
import pojos.Produto;

import javax.persistence.EntityManager;
import java.util.List;


public class ProdutoDAOTest {
    private final Produto produtoMock = new Produto("Sabão Líquido Brilhante Limpeza Total 3L",
            45.9, "7891150020672", 10);

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    @BeforeEach
    private void cleanUp() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("delete from produto").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void shouldCreateProdutoSuccessfully() {
        this.produtoDAO.adicionar(produtoMock);

        Produto produto = this.produtoDAO.procurar(this.produtoMock.getCodigo());
        Assertions.assertNotNull(produto);

        Assertions.assertEquals(this.produtoMock.getCodigo(), produto.getCodigo());
        Assertions.assertEquals(this.produtoMock.getNome(), produto.getNome());
        Assertions.assertEquals(this.produtoMock.getPreco(), produto.getPreco());
        Assertions.assertEquals(this.produtoMock.getQuantidade(), produto.getQuantidade());
    }

    @Test
    public void shouldListZeroProdutoWhenNoOneWasCreated() {
        List<Produto> produtos = this.produtoDAO.listar();
        Assertions.assertEquals(0, produtos.size());
    }

    @Test
    public void shouldListOneProdutoWhenOneWasCreated() {
        this.produtoDAO.adicionar(produtoMock);
        List<Produto> produtos = this.produtoDAO.listar();
        Assertions.assertEquals(1, produtos.size());
    }

    @Test
    public void shouldBeAbleToRemoveProduto() {
        this.produtoDAO.adicionar(produtoMock);
        List<Produto> produtosBeforeRemove = this.produtoDAO.listar();
        Assertions.assertEquals(1, produtosBeforeRemove.size());

        this.produtoDAO.remover(this.produtoMock);
        List<Produto> moradoresAfterRemove = this.produtoDAO.listar();
        Assertions.assertEquals(0, moradoresAfterRemove.size());
    }

    @Test
    public void shouldBeAbleToUpdateProduto() {
        this.produtoDAO.adicionar(produtoMock);

      //  this.produtoMock.setNome("Kit Sabão para diluir OMO 500ml com garrafa");
      //  this.produtoMock.setPreco(21.99);
        /*this.produtoMock.setCodigo(produto.getCodigo()); Código é a chave primária*/
      //  this.produtoMock.setQuantidade(5);

        this.produtoDAO.alterar(this.produtoMock);

        Produto produtoUpdated = this.produtoDAO.procurar(this.produtoMock.getCodigo());
        Assertions.assertNotNull(produtoUpdated);

        Assertions.assertEquals(produtoMock.getNome(), produtoUpdated.getNome());
        Assertions.assertEquals(produtoMock.getPreco(), produtoUpdated.getPreco());
        Assertions.assertEquals(produtoMock.getQuantidade(), produtoUpdated.getQuantidade());
    }
}