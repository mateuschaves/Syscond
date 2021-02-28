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
	public void alterar(Apartamento apartamento) {// pendente
		
		
	}

	@Override
	public Apartamento procurar(int numero) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
