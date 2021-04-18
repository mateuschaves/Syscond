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

    private Apartamento apartamentoMock = new Apartamento("1", "Primeiro", "B");
    private List<Carro> carrosMock = new ArrayList<>();

    private Morador moradorMock = new Morador("115", "Mateus Henrique", this.apartamentoMock, this.carrosMock);

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
            ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
            apartamentoDAO.adicionar(this.apartamentoMock);

            MoradorDAO moradorDao = new MoradorDAO();
            moradorDao.adicionar(this.moradorMock);

            List<Morador> moradores = moradorDao.listar();
            Assert.assertEquals(1, moradores.size());
            Morador morador = moradores.get(0);
            Assert.assertEquals(this.moradorMock.getNome(), morador.getNome());
            Assert.assertEquals(this.moradorMock.getCpf(), morador.getCpf());
        }catch (Exception e) {

        }
    }
}

