package repositorios;

import java.util.ArrayList;
import java.util.List;

import exceptions.fornecedor.FornecedorNaoEncontrado;

import pojos.Fornecedor;

public interface FornecedorDaoInterface {
	
	public Fornecedor procurar(String cnpj);
    public void adicionar(Fornecedor fornecedor);
    public void remover(Fornecedor fornecedor);
    public List<Fornecedor> listar();
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado;
	
}
