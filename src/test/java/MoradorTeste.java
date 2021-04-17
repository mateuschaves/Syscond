import org.junit.Assert;
import org.junit.Test;
import pojos.Apartamento;
import pojos.Morador;

import java.util.ArrayList;

public class MoradorTeste {
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
}

