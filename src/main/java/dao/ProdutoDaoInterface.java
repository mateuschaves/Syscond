package dao;

import pojos.Produto;

import java.util.List;

public interface ProdutoDaoInterface {


    public Produto procurar(String codigoDeBarras);

    public void adicionar(Produto morador);

    public void remover(Produto morador);

    public List<Produto> listar();

    public void alterar(Produto morador);

}
