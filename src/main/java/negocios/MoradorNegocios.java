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

public class MoradorNegocios {

    private ApartamentoDaoInterface apartamentoDao = new ApartamentoDAO();
    private MoradorDaoInterface moradorDao = new MoradorDAO();

    public void cadastrar(Morador morador){

        try{
            moradorDao.adicionar(morador);

        }catch(Exception e){
            System.out.println("MoradorNegocios: Erro: " + e.getMessage());
        }

    }
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
    public void alterar(Morador morador){

        moradorDao.alterar(morador);

    }

    public Morador pesquisar(Morador morador){
        return(moradorDao.procurar(morador));
    }





}
