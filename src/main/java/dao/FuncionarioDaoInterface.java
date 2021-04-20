package dao;

import java.util.ArrayList;

import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import exceptions.funcionario.FuncionarioJaExistente;
import exceptions.funcionario.FuncionarioNaoEncontrado;

import pojos.Funcionario;

public interface FuncionarioDaoInterface {

    /**
     * @param funcionario utilizado para adicionar um funcionario;
     * @throws FuncionarioJaExistente caso um usuario queira adicionar um funcionario que ja existe no
     * banco de dados.
     */
    public void adicionar(Funcionario funcionario) throws FuncionarioJaExistente;

    /**
     * @param funcionario utilizado para alterar um funcionario;
     * @throws FuncionarioNaoEncontrado caso um usuario queira alterar um funcionario que nao existe BD.
     * @throws FuncionarioJaExistente confere se o funcionario existe, pois nao eh possivel alterar algo que
     * nao existe.
     */
    public void alterar(Funcionario funcionario) throws FuncionarioNaoEncontrado, FuncionarioJaExistente;

    /**
     *
     * @return retorna um ArrayList de funcionarios;
     * @throws FuncionarioNaoEncontrado caso o ArrayList esteja vazio.
     */
    public ArrayList<Funcionario> listar() throws FuncionarioNaoEncontrado;

    /**
     * @return retorna um funcionario;
     * @param id utilizado para procurar um funcionario;
     * @throws FuncionarioNaoEncontrado caso um usuario queira procurar um funcionario que nao existe BD.
     */
    public Funcionario procurar(String id) throws FuncionarioNaoEncontrado;

    /**
     * @throws FuncionarioNaoEncontrado caso um usuario queira deletar um funcionario que nao existe BD;
     * @param funcionario utilizado para apagar um funcionario.
     */
    public void remover(Funcionario funcionario) throws FuncionarioNaoEncontrado;

    /**
     * @throws FuncionarioNaoEncontrado caso um usuario queira deletar um funcionario que nao existe BD;
     * @param cpf outra forma de deletar um funcionario.
     */
    public void remover(String cpf) throws FuncionarioNaoEncontrado;
}
