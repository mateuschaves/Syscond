package dao;

import java.util.ArrayList;
import java.util.List;

import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import pojos.Apartamento;

public interface ApartamentoDaoInterface {
	/**
     * @param apartamento utilizado para adicionar um apartamento;
     * @throws ApartamentoJaExistente caso um usuario queira adicionar um apartamento que ja existe no
     * banco de dados;
     */
    public void adicionar(Apartamento apartamento) throws ApartamentoJaExistente;

    /**
     * @param apartamento utilizado para alterar um apartamento;
     * @throws ApartamentoNaoEncontrado caso um usuario queira alterar um apartamento que nao existe;
     * @throws ApartamentoJaExistente confere se o apartamento existe, pois nao eh possivel alterar algo que
     * nao existe;
     */
    public void alterar(Apartamento apartamento) throws ApartamentoNaoEncontrado, ApartamentoJaExistente;

    /**
     * @return uma lista de apartamentos;
     * @throws ApartamentoNaoEncontrado caso a lista esteja vazia.
     */
    public List<Apartamento> listar() throws ApartamentoNaoEncontrado;

    /**
     * @param numero procura o apartamento pelo seu numero;
     * @return retorna um apartamento;
     * @throws ApartamentoNaoEncontrado caso um usuario queira procurar um apartamento que nao existe.
     */
    public Apartamento procurar(String numero) throws ApartamentoNaoEncontrado;

    /**
     * @param apartamento procura o apartamento pelo seu numero;
     * @return retorna um apartamento;
     * @throws ApartamentoNaoEncontrado caso um usuario queira procurar um apartamento que nao existe.
     */
    public Apartamento procurar(Apartamento apartamento);s

    /**
     * @param apartamento caso um usuario queira apagar um apartamento;
     * @throws ApartamentoNaoEncontrado caso um usuario queira deletar um apartamento que nao existe.
     */
    public void remover(Apartamento apartamento) throws ApartamentoNaoEncontrado;
}
