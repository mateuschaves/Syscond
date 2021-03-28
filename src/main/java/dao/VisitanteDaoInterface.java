package dao;

import pojos.Produto;
import pojos.Visitante;

import java.util.List;

public interface VisitanteDaoInterface {


    public Visitante procurar(String cpf);

    public void adicionar(Visitante visitante);

    public void remover(Visitante visitante);

    public List<Visitante> listar();

    public void alterar(Visitante visitante);

}
