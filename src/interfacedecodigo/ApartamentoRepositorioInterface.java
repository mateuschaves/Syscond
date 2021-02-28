package interfacedecodigo;

import java.util.ArrayList;
import pojos.Apartamento;
import exceptions.ApartamentoJaExistente;
import exceptions.ApartamentoNaoEncontrado;

public interface ApartamentoRepositorioInterface {
	
    public void adicionar(Apartamento apartamento, int... posicao) throws ApartamentoJaExistente;
    public void alterar(int numero, Apartamento novo) throws ApartamentoNaoEncontrado, ApartamentoJaExistente;
    public ArrayList<Apartamento> listar() throws ApartamentoNaoEncontrado;
    public Apartamento procurar(int numero) throws ApartamentoNaoEncontrado;
    public void remover(Apartamento apartamento) throws ApartamentoNaoEncontrado;
}
