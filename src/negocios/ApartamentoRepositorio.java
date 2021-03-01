/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import java.util.ArrayList;
import pojos.Apartamento;
import interfacedecodigo.ApartamentoRepositorioInterface;
import exceptions.ApartamentoNaoEncontrado;
import exceptions.ApartamentoJaExistente;

/**
 *
 * @author Mattskywalker
 */

public class ApartamentoRepositorio implements ApartamentoRepositorioInterface {

	private ArrayList<Apartamento> todosApartamentos = new ArrayList<Apartamento>();

	public void adicionar(Apartamento apartamento, int... posicao) throws ApartamentoJaExistente {
			try {
				procurar(apartamento.getNumero());
				throw new ApartamentoJaExistente(apartamento.getNumero());
			} catch (ApartamentoNaoEncontrado e) {
				if (posicao.length > 0)
					this.todosApartamentos.add(posicao[0], apartamento);
				else
					this.todosApartamentos.add(apartamento);
			}
	}

	public void alterar(int numero, Apartamento apartamento) throws ApartamentoNaoEncontrado, ApartamentoJaExistente {
		Apartamento velho = procurar(numero);
		int posicao = this.todosApartamentos.indexOf(velho);
		remover(velho);
		adicionar(apartamento, posicao);
	}

	public ArrayList<Apartamento> listar() throws ApartamentoNaoEncontrado {
		if (this.todosApartamentos.size() == 0)
			throw new ApartamentoNaoEncontrado(0);
		return this.todosApartamentos;
	}

	public Apartamento procurar(int numero) throws ApartamentoNaoEncontrado {
		for (Apartamento apartamento : this.todosApartamentos) {
			if (apartamento.getNumero() == numero) {
				return apartamento;
			}
		}
		throw new ApartamentoNaoEncontrado(numero);
	}

	public void remover(Apartamento apartamento) {
		this.todosApartamentos.remove(apartamento);
	}

}
