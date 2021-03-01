package interfacedecodigo;

import java.util.ArrayList;
import pojos.Produto;
import exceptions.ProdutoNaoEncontrado;
import exceptions.ProdutoJaExistente;


public interface AlmoxarifacoInterface {
	
    public void adicionar(Produto produto, int... posicao) throws ProdutoJaExistente;
    public void alterar(String codigoDeBarras, Produto novo) throws ProdutoNaoEncontrado, ProdutoJaExistente;
    public ArrayList<Produto> listar() throws ProdutoNaoEncontrado;
    public Produto procurar(String codigoDeBarras) throws ProdutoNaoEncontrado;
}

    
