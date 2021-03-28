package dao;

import pojos.Produto;

import java.util.List;

public interface ProdutoDaoInterface {


    public Produto procurar(String codigoDeBarras);

    public void adicionar(Produto produto);

    public void remover(Produto produto);

    public List<Produto> listar();

    public void alterar(Produto produto);

}
