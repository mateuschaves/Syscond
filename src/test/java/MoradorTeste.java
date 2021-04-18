import org.junit.Assert;
import org.junit.Test;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;
import dao.MoradorDAO;
import dao.ApartamentoDAO;

import java.util.ArrayList;
import java.util.List;


public class MoradorTeste {

    private final Apartamento apartamentoMock = new Apartamento("1", "Primeiro", "B");
    private final List<Carro> carrosMock = new ArrayList<>();
    private final Morador moradorMock = new Morador("115", "Mateus Henrique", this.apartamentoMock, this.carrosMock);

    private final MoradorDAO moradorDao = new MoradorDAO();
    private final ApartamentoDAO apartamentoDAO = new ApartamentoDAO();



    private void createMorador() {
        try {
            this.apartamentoDAO.adicionar(this.apartamentoMock);
            this.moradorDao.adicionar(this.moradorMock);
        }catch (Exception e) {
            System.out.println("createMorador failed");
        }
    }

    @Test
    public void testNome(){
        Assert.assertEquals(
                "Eduardo",
                (new Morador("1", "Eduardo", new Apartamento(), new ArrayList<>())).getNome());
    }

    @Test
    public void testCPF(){
        Assert.assertEquals(
                "1",
                (new Morador("1", "Eduardo", new Apartamento(), new ArrayList<>())).getCpf());
    }

    @Test
    public void shouldCreateMoradorSuccessfully(){
        try {
            this.createMorador();
            List<Morador> moradores = this.moradorDao.listar();
            Assert.assertEquals(1, moradores.size());
            Morador morador = moradores.get(0);
            Assert.assertEquals(this.moradorMock.getNome(), morador.getNome());
            Assert.assertEquals(this.moradorMock.getCpf(), morador.getCpf());
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
        this.createMorador();
        List<Morador> moradoresBeforeRemove = this.moradorDao.listar();
        Assert.assertEquals(1, moradoresBeforeRemove.size());
        this.moradorDao.remover(this.moradorMock);
        List<Morador> moradoresAfterRemove = this.moradorDao.listar();
        Assert.assertEquals(0, moradoresAfterRemove.size());
    }

    @Test
    public void shouldBeAbleToUpdateMorador(){
        String newName = "Mateus Henrique Editado";
        this.createMorador();
        this.moradorMock.setNome(newName);
        this.moradorDao.alterar(this.moradorMock);
        Morador moradorUpdated = this.moradorDao.procurar(this.moradorMock.getCpf());
        Assert.assertEquals(newName, moradorUpdated.getNome());
    }
}

