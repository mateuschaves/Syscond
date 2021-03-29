package dao;

import java.util.ArrayList;

import exceptions.funcionario.FuncionarioJaExistente;
import exceptions.funcionario.FuncionarioNaoEncontrado;

import pojos.Funcionario;

public interface FuncionarioDaoInterface {

    public void adicionar(Funcionario funcionario) throws FuncionarioJaExistente;

    public void alterar(Funcionario funcionario) throws FuncionarioNaoEncontrado;

    public ArrayList<Funcionario> listar() throws FuncionarioNaoEncontrado;

    public Funcionario procurar(String id) throws FuncionarioNaoEncontrado;

    public void remover(Funcionario funcionario) throws FuncionarioNaoEncontrado;

    public void remover(String cpf) throws FuncionarioNaoEncontrado;
}
