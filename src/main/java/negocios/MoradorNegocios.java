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
