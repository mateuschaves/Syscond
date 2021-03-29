package dao;


import exceptions.morador.MoradorJaExistente;
import exceptions.morador.MoradorNaoEncontrado;
import pojos.Morador;

import java.util.List;

public interface MoradorDaoInterface {

    public Morador procurar(String cpf) throws MoradorNaoEncontrado;
    public void adicionar(Morador morador) throws MoradorJaExistente;
    public void remover(Morador morador) throws MoradorNaoEncontrado;
    public List<Morador> listar() throws MoradorNaoEncontrado;
    public void alterar(Morador morador) throws MoradorNaoEncontrado;

}
