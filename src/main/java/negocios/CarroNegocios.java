package negocios;

import dao.CarroDAO;
import dao.CarroDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;
import pojos.Carro;
import pojos.Morador;

import java.util.List;

public class CarroNegocios {

    CarroDaoInterface carroDao = new CarroDAO();
    MoradorDaoInterface moradorDao = new MoradorDAO();

    public void cadastrar(Carro carro){
        try {
            carroDao.adicionar(carro);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Carro Negocios: Erro ao adicionar carro!" + e.getMessage());
        }
    }

    public void deletar(Carro carro){
        try {
            carroDao.remover(carro);
        }catch (Exception e){
            System.out.println("CarroNegocios: Erro ao deletar um carro!" + e.getMessage());
        }
    }

    public void alterar(Carro carro){

        try {
            carroDao.alterar(carro);
        }catch (Exception e){
            System.out.println("CarroNegocios: Erro ao alterar um carro!" + e.getMessage());
        }
    }

    public List<Carro> listarCarros(){
        int index = 0;
        List<Carro> listaCarros = null;

        try{
            return listaCarros = carroDao.listar();


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Carro Negocios: Erro: " + e.getMessage());
            return null;
        }

    }

    public Carro pesquisar(Carro carro){
        return (carroDao.procurar(carro));
    }



}
