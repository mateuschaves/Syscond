package dao;

import exceptions.visitante.VisitanteJaExistente;
import exceptions.visitante.VisitanteNaoEncontrado;
import pojos.Produto;
import pojos.Visitante;

import java.util.List;

public interface VisitanteDaoInterface {


    public Visitante procurar(String cpf) throws VisitanteNaoEncontrado;

    public void adicionar(Visitante visitante) throws VisitanteJaExistente;

    public void remover(Visitante visitante) throws VisitanteNaoEncontrado;

    public List<Visitante> listar() throws VisitanteNaoEncontrado;

    public void alterar(Visitante visitante) throws VisitanteNaoEncontrado;

}
