package interfacedecodigo;

import java.util.ArrayList;

import exceptions.produto.ProdutoJaExistente;
import exceptions.produto.ProdutoNaoEncontrado;
import pojos.Produto;


public interface AlmoxarifadoInterface {
	
    public void adicionar(Produto produto, int... posicao) throws ProdutoJaExistente;
    public void alterar(String codigoDeBarras, Produto novo) throws ProdutoNaoEncontrado, ProdutoJaExistente;
    public ArrayList<Produto> listar() throws ProdutoNaoEncontrado;
    public Produto procurar(String codigoDeBarras) throws ProdutoNaoEncontrado;
}

    
