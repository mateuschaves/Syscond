package negocios;

import dao.ApartamentoDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;

public class TesteApartamentos {
    public static void main(String [] args){

        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();
        MoradorNegocios moradorNegocios = new MoradorNegocios();

        apartamentoNegocios.cadastrar("636","1","B");
        apartamentoNegocios.cadastrar("200","1","B");
        apartamentoNegocios.cadastrar("300","1","B");
        apartamentoNegocios.cadastrar("1","1","B");

        apartamentoNegocios.deletar("636");

        apartamentoNegocios.alterarID("300","3");
        apartamentoNegocios.alterar("1","2","C");
        apartamentoNegocios.alterarAndar("3","30");
        apartamentoNegocios.alterarBloco("200","BLOCO");


        apartamentoNegocios.listarApartamentos();

        //metodos da camada de negocios de moraodor;

        moradorNegocios.cadastrar("123.623.444-32","Manuela D'avila Gráça","1");

        moradorNegocios.deletar("123.623.444-32");

        moradorNegocios.listarMoradores();


    }
}
