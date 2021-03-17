/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;
import java.util.ArrayList;

import exceptions.funcionario.FuncionarioN�oEncontrado;
import pojos.Funcionario;
/**
 *
 * @author Mattskywalker
 */
public interface FuncionarioRepositorioInterface {
    
	public Funcionario procurar(String id) throws FuncionarioN�oEncontrado;
	public Funcionario procurar(Funcionario funcionario) throws FuncionarioN�oEncontrado;
    public void adicionar(Funcionario funcionario);
    public void remover(Funcionario funcionario);
    public ArrayList<Funcionario> listar();
    public void alterar(Funcionario funcionario) throws FuncionarioN�oEncontrado;
	
    
    
}
