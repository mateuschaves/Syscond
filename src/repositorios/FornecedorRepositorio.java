/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;
import java.util.ArrayList;

import exceptions.fornecedor.FornecedorNãoEncontrado;
import interfacedecodigo.FornecedorRepositorioInterface;
import pojos.Fornecedor;
/**
 *
 * @author Mattskywalker
 */
public class FornecedorRepositorio implements FornecedorRepositorioInterface{
    
    private ArrayList<Fornecedor> todosFornecedores = new ArrayList<>();
    
    public Fornecedor procurar(String cpf) throws FornecedorNãoEncontrado {
		
		for(Fornecedor a : this.todosFornecedores){
			
			if(a.getCnpj().equals(cpf)) {
				return a;
			}
			
		}
		
		
		return null;
	}

	@Override
	public Fornecedor procurar(Fornecedor fornecedor) throws FornecedorNãoEncontrado{
		
		for(Fornecedor a : this.todosFornecedores) {
			
			if(a.equals(fornecedor)) {
				
				return a;
				
			}
		}
		
		return null;
	}

	@Override
	public void adicionar(Fornecedor fornecedor) {
		
		this.todosFornecedores.add(fornecedor);
		
	}

	@Override
	public void remover(Fornecedor fornecedor) {
		

		this.todosFornecedores.remove(fornecedor);
		
	}

	@Override
	public ArrayList<Fornecedor> listar() {
		
		return this.todosFornecedores;
	}

	@Override
	public void alterar(Fornecedor fornecedor) throws FornecedorNãoEncontrado {
		
		Fornecedor cadastrado = this.procurar(fornecedor.getCnpj());
		int indice = this.todosFornecedores.indexOf(cadastrado);
		
		this.todosFornecedores.remove(indice);
		this.todosFornecedores.add(indice, fornecedor);
		
		
	}
    
}
