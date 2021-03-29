package dao;


import exceptions.carro.CarroJaExistente;
import exceptions.carro.CarroNaoEncontrado;
import pojos.Carro;


import java.util.List;

public interface CarroDaoInterface {

    public Carro procurar(String placa) throws CarroNaoEncontrado;
    public void adicionar(Carro carro) throws CarroJaExistente;
    public void remover(Carro carro) throws CarroNaoEncontrado;
    public List<Carro> listar() throws CarroNaoEncontrado;
    public void alterar(Carro carro) throws CarroNaoEncontrado;
}
