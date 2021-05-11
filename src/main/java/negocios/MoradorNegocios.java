package negocios;

import dao.ApartamentoDAO;
import dao.ApartamentoDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;
import pojos.Visitante;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Nessa classe de MoradorNegocios sera chamada a MoradorDAO para fazer um CRUD que tenha relacao com um BD.
 */
public class MoradorNegocios {

    private ApartamentoDaoInterface apartamentoDao = new ApartamentoDAO();
    private MoradorDaoInterface moradorDao = new MoradorDAO();

    /**
     *
     * @param morador parametro utilizado para cadastrar um morador.
     */
    public void cadastrar(Morador morador){

        try{
            moradorDao.adicionar(morador);

        }catch(Exception e){
            System.out.println("MoradorNegocios: Erro: " + e.getMessage());
        }

    }

    /**
     *
     * @param morador parametro utilizado para apagar um morador.
     */
    public void deletar(Morador morador){
        try{

            VisitanteNegocios visitanteNegocios = new VisitanteNegocios();
            CarroNegocios carroNegocios = new CarroNegocios();

            morador = this.pesquisar(morador);
            System.err.println("Nome " + morador.getNome());

            //primeiro é preciso deletar os carros e os visitantes;
            Collection<Carro> carroList = morador.getCarros();
            Collection<Visitante> visitanteList = morador.getVisitantesList();

            for(Carro a: carroList){
                carroNegocios.deletar(a);
            }
            for(Visitante a: visitanteList){
                visitanteNegocios.deletar(a);
            }
            //atualizamos o objeto, pois agora ele não tem mais visitantes;
            morador = this.pesquisar(morador);

            moradorDao.remover(morador);
            
        }catch(Exception e){
            System.out.println("MoradorNegocios: Erro: " + e.getMessage());
        }
    }

    /**
     *
     * @return retorna uma lista de moradores.
     */
    public List<Morador> listarMoradores(){
        int index = 0;
        List<Morador> listaMoradores = null;

        try{
            return listaMoradores = moradorDao.listar();
        }catch(Exception e){
            System.out.println("MoradorNegocios: Erro: " + e.getMessage());
            return null;
        }

    }

    /**
     *
     * @param morador parametro utilizado para alterar um morador.
     */
    public void alterar(Morador morador){

        moradorDao.alterar(morador);

    }

    /**
     *
     * @param morador parametro utilizado para procurar um morador, quando acha
     * @return retorna um morador pesquisado.
     */
    public Morador pesquisar(Morador morador){
        return(moradorDao.procurar(morador));
    }





}
