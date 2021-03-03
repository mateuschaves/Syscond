/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;
import java.util.ArrayList;

import interfacedecodigo.FuncionarioRepositorioInterface;
import pojos.Funcionario;

/**
 *
 * @author Mattskywalker
 */
public class FuncionarioRepositorio implements FuncionarioRepositorioInterface{ 
    
    private ArrayList<Funcionario> allFuncionarios = new ArrayList<>();

	@Override
	public Funcionario procurar(String cpf) {
		
		for(Funcionario a : this.allFuncionarios) {
			
			if(a.getCpf().equals(cpf)) {
				return a;
			}
			
		}
		
		
		return null;
	}

	@Override
	public Funcionario procurar(Funcionario funcionario) {
		
		for(Funcionario a : this.allFuncionarios) {
			
			if(a.equals(funcionario)) {
				
				return a;
				
			}
		}
		
		return null;
	}

	@Override
	public void adicionar(Funcionario funcionario) {
		
		this.allFuncionarios.add(funcionario);
		
	}

	@Override
	public void remover(Funcionario funcionario) {
		

		this.allFuncionarios.remove(funcionario);
		
	}

	@Override
	public ArrayList<Funcionario> listar() {
		
		return this.allFuncionarios;
	}

	@Override
	public void alterar(Funcionario funcionario) {
		
		Funcionario cadastrado = this.procurar(funcionario.getCpf());
		int indice = this.allFuncionarios.indexOf(cadastrado);
		
		this.allFuncionarios.remove(indice);
		this.allFuncionarios.add(indice, funcionario);
		
		
	}

	

	
	
}
    
    
    
    
    
    

