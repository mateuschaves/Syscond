package dao;

import java.util.ArrayList;
import java.util.List;

import exceptions.fornecedor.FornecedorJaExistente;
import exceptions.fornecedor.FornecedorNaoEncontrado;

import pojos.Fornecedor;

public interface FornecedorDaoInterface {
	
	public Fornecedor procurar(String cnpj);
    public void adicionar(Fornecedor fornecedor) throws FornecedorJaExistente;
    public void remover(Fornecedor fornecedor);
    public List<Fornecedor> listar();
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado;
	
}
