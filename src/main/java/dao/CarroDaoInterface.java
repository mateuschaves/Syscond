package dao;


import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import pojos.Carro;


import java.util.List;

/**
 * @author grupo Sith; Interface de carro com suas assinaturas
 */
public interface CarroDaoInterface {

    /**
     * @param placa utilizado para procurar um carro;
     * @return retorna um carro.
     */
    public Carro procurar(String placa);

    /**
     * @param carro utilizado para procurar um carro;
     * @return retorna um carro.
     */
    public Carro procurar(Carro carro);

    /**
     * @param carro utilizado para adicionar um carro.
     */
    public void adicionar(Carro carro);

    /**
     * @param carro utilizado para remover um carro.
     */
    public void remover(Carro carro);

    /**
     * @return uma lista de carros.
     */
    public List<Carro> listar();

    /**
     * @param carro utilizado para alterar um carro.
     */
    public void alterar(Carro carro);
}
