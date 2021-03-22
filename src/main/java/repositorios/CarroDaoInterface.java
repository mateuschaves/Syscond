package repositorios;


import pojos.Carro;


import java.util.List;

public interface CarroDaoInterface {

    public Carro procurar(String placa);
    public void adicionar(Carro carro);
    public void remover(Carro carro);
    public List<Carro> listar();
    public void alterar(Carro carro);
}
