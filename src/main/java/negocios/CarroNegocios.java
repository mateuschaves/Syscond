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

    public void listarCarros(){
        int index = 0;
        List<Carro> listaCarros = null;

        try{
            listaCarros = carroDao.listar();
            System.out.println("Listando Carros: ");
            for (Carro a:listaCarros) {
                index++;
                System.out.println("");
                System.out.println(index + " Carro:");
                System.out.println("Placa: " + a.getPlaca());
                System.out.println("Cor: " + a.getCor());
                System.out.println("Modelo: " + a.getModelo());
                System.out.println("Dono do Carro: " + a.getProprietario().getNome());
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Carro Negocios: Erro: " + e.getMessage());
        }

    }

    public Carro pesquisar(Carro carro){
        return (carroDao.procurar(carro));
    }



}
