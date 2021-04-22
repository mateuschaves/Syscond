package negocios;

public class Teste {
    public static void main(String [] args){

        ApartamentoNegocios apartamentoNegocios = new ApartamentoNegocios();

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

    }
}
