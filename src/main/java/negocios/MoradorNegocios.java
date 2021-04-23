package negocios;

import dao.ApartamentoDAO;
import dao.ApartamentoDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;
import pojos.Visitante;

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
    public void listarMoradores(){
        int index = 0;
        List<Morador> listaMoradores = null;

        try{
            listaMoradores = moradorDao.listar();
            System.out.println("Listando Moradores: ");
            for (Morador a:listaMoradores) {
                index++;
                System.out.println("");
                System.out.println(index + "º Morador:");
                System.out.println("Nome: " + a.getNome());
                System.out.println("Cpf: " + a.getCpf());
                System.out.println("Apartamento: Numero " + a.getApartamento().getNumero());

            }
        }catch(Exception e){
            System.out.println("MoradorNegocios: Erro: " + e.getMessage());
        }

    }
    public void alterar(Morador morador){

        moradorDao.alterar(morador);

    }

    public Morador pesquisar(Morador morador){
        return(moradorDao.procurar(morador));
    }





}
