package dao;

import java.util.ArrayList;
import java.util.List;

import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import exceptions.fornecedor.FornecedorJaExistente;
import exceptions.fornecedor.FornecedorNaoEncontrado;

import pojos.Fornecedor;

/**
 * @author grupo Sith; Interface de fornecedor com suas assinaturas
 */
public interface FornecedorDaoInterface {

    /**
     * @return retorna um fornecedor;
     * @param cnpj utilizado para procurar um fornecedor;
     * @throws FornecedorNaoEncontrado caso um usuario queira procurar um fornecedor que nao existe no BD.
     */
	public Fornecedor procurar(String cnpj) throws FornecedorNaoEncontrado;

	/**
     * @throws FornecedorJaExistente caso o usuario tente adicionar um fornecedor que ja existe no BD;
     * @param fornecedor utilizado para adicionar um fornecedor.
     */
	public void adicionar(Fornecedor fornecedor) throws FornecedorJaExistente;

    /**
     * @throws FornecedorNaoEncontrado caso o usuario tente deletar um fornecedor que nao existe no BD;
     * @param fornecedor utilizado para apagar um fornecedor.
     */
    public void remover(Fornecedor fornecedor) throws FornecedorNaoEncontrado;

    /**
     *
     * @return retorna uma lista de fornecedores;
     * @throws FornecedorNaoEncontrado caso a lista esteja vazia.
     */
    public List<Fornecedor> listar() throws FornecedorNaoEncontrado;
    /**
     * @throws FornecedorNaoEncontrado caso o usuario tente alterar um fornecedor que nao existe no BD;
     * @throws FornecedorJaExistente confere se o fornecedor existe, pois nao eh possivel alterar algo que
     * nao existe;
     * @param fornecedor utilizado para alterar um fornecedor.
     */
    public void alterar(Fornecedor fornecedor) throws FornecedorNaoEncontrado, FornecedorJaExistente;
	
}
