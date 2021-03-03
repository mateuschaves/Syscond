package interfacedecodigo;

import java.util.ArrayList;

import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import pojos.Apartamento;

public interface ApartamentoRepositorioInterface {
	
    public void adicionar(Apartamento apartamento, int... posicao) throws ApartamentoJaExistente;
    public void alterar(int numero, Apartamento novo) throws ApartamentoNaoEncontrado, ApartamentoJaExistente;
    public ArrayList<Apartamento> listar() throws ApartamentoNaoEncontrado;
    public Apartamento procurar(int numero) throws ApartamentoNaoEncontrado;
    public void remover(Apartamento apartamento) throws ApartamentoNaoEncontrado;
}
