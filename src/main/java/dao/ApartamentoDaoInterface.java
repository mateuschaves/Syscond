package dao;

import java.util.ArrayList;
import java.util.List;

import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import pojos.Apartamento;

public interface ApartamentoDaoInterface {
	
    public void adicionar(Apartamento apartamento) throws ApartamentoJaExistente;
    public void alterar(Apartamento apartamento) throws ApartamentoNaoEncontrado, ApartamentoJaExistente;
    public List<Apartamento> listar() throws ApartamentoNaoEncontrado;
    public Apartamento procurar(String numero) throws ApartamentoNaoEncontrado;
    public void remover(Apartamento apartamento) throws ApartamentoNaoEncontrado;
}
