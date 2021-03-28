package dao;

import java.util.ArrayList;
import java.util.List;

import exceptions.fornecedor.FornecedorJaExistente;
import exceptions.fornecedor.FornecedorNaoEncontrado;

import pojos.Fornecedor;

public interface FornecedorDaoInterface {
	
	public Fornecedor procurar(String cnpj) throws FornecedorNaoEncontrado;
    public void adicionar(Fornecedor fornecedor) throws FornecedorJaExistente;
    public void remover(Fornecedor fornecedor) throws FornecedorNaoEncontrado;
    public List<Fornecedor> listar() throws FornecedorNaoEncontrado;
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado, FornecedorJaExistente;
	
}
