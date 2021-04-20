package dao;

import pojos.Produto;
import pojos.Visitante;

import java.util.List;

public interface VisitanteDaoInterface {

    /**
     *
     * @param cpf utilizado para procurar um visitante;
     * @return retorna um visitante.
     */
    public Visitante procurar(String cpf);

    /**
     *
     * @param visitante utilizado para adicionar um visitante.
     */
    public void adicionar(Visitante visitante);

    /**
     *
     * @param visitante utilizado para deletar um visitante.
     */
    public void remover(Visitante visitante);

    /**
     *
     * @return retorna uma lista de visitantes.
     */
    public List<Visitante> listar();

    /**
     *
     * @param visitante utilizado para alterar um visitante.
     */
    public void alterar(Visitante visitante);

}
