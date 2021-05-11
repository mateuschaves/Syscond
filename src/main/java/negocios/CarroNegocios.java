package negocios;

import dao.CarroDAO;
import dao.CarroDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;
import pojos.Carro;
import pojos.Morador;

import java.util.List;

/**
 * Nessa classe de CarroNegocios sera chamada a CarroDAO para fazer um CRUD que tenha relacao com um BD.
 */
public class CarroNegocios {

    CarroDaoInterface carroDao = new CarroDAO();
    MoradorDaoInterface moradorDao = new MoradorDAO();

    /**
     *
     * @param carro eh o parametro utilizado para cadastrar um carro.
     */
    public void cadastrar(Carro carro){
        try {
            carroDao.adicionar(carro);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Carro Negocios: Erro ao adicionar carro!" + e.getMessage());
        }
    }

    /**
     *
     * @param carro eh o parametro utilizado para deletar um carro.
     */
    public void deletar(Carro carro){
        try {
            carroDao.remover(carro);
        }catch (Exception e){
            System.out.println("CarroNegocios: Erro ao deletar um carro!" + e.getMessage());
        }
    }

    /**
     *
     * @param carro eh o parametro utilizado para alterar um carro.
     */
    public void alterar(Carro carro){

        try {
            carroDao.alterar(carro);
        }catch (Exception e){
            System.out.println("CarroNegocios: Erro ao alterar um carro!" + e.getMessage());
        }
    }

    /**
     *
     * @return retorna uma lista de carros.
     */
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

    /**
     *
     * @param carro eh o parametro utilizado para procurar um carro e quando encontra,
     * @return retorna o carro que foi pesquisado.
     */
    public Carro pesquisar(Carro carro){
        return (carroDao.procurar(carro));
    }



}
