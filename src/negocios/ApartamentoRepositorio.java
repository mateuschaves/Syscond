/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import java.util.ArrayList;
import pojos.Apartamento;
import interfacedecodigo.ApartamentoRepositorioInterface;

/**
 *
 * @author Mattskywalker
 */

public class ApartamentoRepositorio implements ApartamentoRepositorioInterface {

    private ArrayList<Apartamento> todosApartamentos = new ArrayList<>();
    
    
    @Override
	public Apartamento procurar(int numero) throws Exception {
		Apartamento retorno = null;
		
		for(Apartamento ap : this.todosApartamentos) {
			if(ap.getNumero() == numero) {
				
				int index  = this.todosApartamentos.indexOf(ap);
				
				retorno = this.todosApartamentos.get(index);
				
			}
		}
		
		if(retorno == null) {
			throw new Exception("Apartamento numero: " + numero +" não encontrado ou não cadastrado no sistema"); 
			
		}
		
		return retorno;
	}

	@Override
	public void adicionar(Apartamento apartamento) {
		// 
		this.todosApartamentos.add(apartamento);
	}

	@Override
	public void remover(Apartamento apartamento) {
		
		this.todosApartamentos.remove(apartamento);
		
	}

	@Override
	public ArrayList<Apartamento> listar() {
		
		return this.todosApartamentos;
		
	}
	
	
	

	@Override
	public void alterar(int numero, Apartamento apartamento) throws Exception{
		
		if(this.procurar(numero).equals(apartamento)) {
			
			int index = this.todosApartamentos.indexOf(apartamento);
			this.todosApartamentos.add(index, apartamento);
			
		}
	}
    
}
