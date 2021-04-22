package negocios;

import dao.ApartamentoDAO;
import dao.ApartamentoDaoInterface;
import pojos.Apartamento;
import utils.Rulles;

import java.util.List;

public class ApartamentoNegocios {

    Rulles rulles = new Rulles();
    ApartamentoDaoInterface apartamentoDao = new ApartamentoDAO();

    public void cadastrar(String numero, String andar, String bloco){
        Apartamento ap = new Apartamento(numero,andar,bloco);

        try{
            apartamentoDao.adicionar(ap);
        }catch (Exception e){

        }
    }
    public void deletar(String numero){
        Apartamento ap = new Apartamento(numero);
        try{
            apartamentoDao.remover(ap);
        }catch(Exception e){

        }
    }

    public void listarApartamentos(){
        int index = 0;
        List<Apartamento> listaApartamentos = null;

        try{
            listaApartamentos = apartamentoDao.listar();
            System.out.println("Listando Apartamentos: ");
            for (Apartamento a:listaApartamentos) {
                index++;
                System.out.println("");
                System.out.println(index + "º Apartamento:");
                System.out.println("Numero: " + a.getNumero());
                System.out.println("Andar: " + a.getAndar());
                System.out.println("Bloco: " + a.getBloco());

            }
        }catch(Exception e){
            System.out.println("ApartamentoNegocios: Erro: " + e.getMessage());
        }

    }

    public void alterarID(String numero, String novoNumero){
        Apartamento target = null;
        try{
            target = apartamentoDao.procurar(numero);
            apartamentoDao.remover(target);
            target.setNumero(novoNumero);
            apartamentoDao.adicionar(target);

        }catch (Exception e){
            System.out.println("ApartamentoNegocios: Erro: " + e.getMessage());
        }

    }

    public void alterar(String numero,String andar,String bloco){
        Apartamento target = new Apartamento(numero,andar,bloco);

        try {
            apartamentoDao.alterar(target);
        }catch (Exception e){
            System.out.println("ApartamerntoNegocios: Erro: ");
        }
    }
    public void alterarBloco(String numero,String bloco){
        Apartamento target = null;

        try {
            target = apartamentoDao.procurar(numero);
            target.setBloco(bloco);
            apartamentoDao.alterar(target);
        }catch (Exception e){
            System.out.println("ApartamerntoNegocios: Erro: ");
        }
    }
    public void alterarAndar(String numero,String andar){
        Apartamento target = null;

        try {
            target = apartamentoDao.procurar(numero);
            target.setAndar(andar);
            apartamentoDao.alterar(target);
        }catch (Exception e){
            System.out.println("ApartamerntoNegocios: Erro: ");
        }
    }

}
