package dao;

import exceptions.produto.ProdutoJaExistente;
import exceptions.produto.ProdutoNaoEncontrado;
import pojos.Produto;

import java.util.List;

public interface ProdutoDaoInterface {


    public Produto procurar(String codigoDeBarras) throws ProdutoNaoEncontrado;

    public void adicionar(Produto produto) throws ProdutoJaExistente;

    public void remover(Produto produto) throws ProdutoNaoEncontrado;

    public List<Produto> listar() throws ProdutoNaoEncontrado;

    public void alterar(Produto produto) throws ProdutoNaoEncontrado;

}
