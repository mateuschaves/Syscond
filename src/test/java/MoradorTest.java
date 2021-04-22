import dao.ApartamentoDAO;
import dao.MoradorDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MoradorTest {

    private final List<Carro> carrosMock = new ArrayList<>();

    private final MoradorDAO moradorDao = new MoradorDAO();
    private final ApartamentoDAO apartamentoDAO = new ApartamentoDAO();



    private Morador createMorador() {
        try {
            Random rand = new Random();
            Apartamento apartamentoMock = new Apartamento(Integer.toString(rand.nextInt()), "Primeiro", "B");
            Morador moradorMock = new Morador(Integer.toString(rand.nextInt()), "Mateus Henrique", apartamentoMock, this.carrosMock);

            this.apartamentoDAO.adicionar(apartamentoMock);
            this.moradorDao.adicionar(moradorMock);

            return moradorMock;
        }catch (Exception e) {
            System.out.println("createMorador failed");
            return null;
        }
    }

    @Before
    public void cleanUp(){
        System.out.println("Limpando dados...");
        this.apartamentoDAO.cleanUp();
        this.moradorDao.cleanUp();
        System.out.println("Dados limpos");
    }

    @Test
    public void testNome(){
        Assert.assertEquals(
                "Eduardo",
                (new Morador("1", "Eduardo", new Apartamento(), new ArrayList<Carro>())).getNome());
    }

    @Test
    public void testCPF(){
        Assert.assertEquals(
                "1",
                (new Morador("1", "Eduardo", new Apartamento(), new ArrayList<Carro>())).getCpf());
    }

    @Test
    public void shouldCreateMoradorSuccessfully(){
        try {
            Morador moradorMock = this.createMorador();
            Assert.assertNotNull(moradorMock);
            List<Morador> moradores = this.moradorDao.listar();
            Assert.assertEquals(1, moradores.size());
            Morador morador = moradores.get(0);
            Assert.assertEquals(moradorMock.getNome(), morador.getNome());
            Assert.assertEquals(moradorMock.getCpf(), morador.getCpf());
        }catch (Exception e) {
            System.out.println("shouldCreateMoradorSuccessfully failed");
        }
    }

    @Test
    public void shouldListZeroMoradorWhenNoOneWasCreated(){
        List<Morador> moradores = this.moradorDao.listar();
        Assert.assertEquals(0, moradores.size());
    }

    @Test
    public void shouldListOneMoradorWhenOneWasCreated(){
        this.createMorador();
        List<Morador> moradores = this.moradorDao.listar();
        Assert.assertEquals(1, moradores.size());
    }

    @Test
    public void shouldBeAbleToRemoveMorador(){
        Morador moradorMock = this.createMorador();
        List<Morador> moradoresBeforeRemove = this.moradorDao.listar();
        Assert.assertEquals(1, moradoresBeforeRemove.size());
        this.moradorDao.remover(moradorMock);
        List<Morador> moradoresAfterRemove = this.moradorDao.listar();
        Assert.assertEquals(0, moradoresAfterRemove.size());
    }

    @Test
    public void shouldBeAbleToUpdateMorador(){
        String newName = "Mateus Henrique Editado";
        Morador moradorMock = this.createMorador();
        Assert.assertNotNull(moradorMock);
        moradorMock.setNome(newName);
        this.moradorDao.alterar(moradorMock);
        Morador moradorUpdated = this.moradorDao.procurar(moradorMock.getCpf());
        Assert.assertEquals(newName, moradorUpdated.getNome());
    }
}
