package dao;


import pojos.Morador;

import java.util.List;

public interface MoradorDaoInterface {

    /**
     *
     * @param cpf utilizado para procurar um morador;
     * @return retorna um morador.
     */
    public Morador procurar(String cpf);

    /**
     *
     * @param morador utilizado para inserir um morador.
     */
    public void adicionar(Morador morador);

    /**
     *
     * @param morador utilizado para apagar um morador.
     */
    public void remover(Morador morador);

    /**
     *
     * @return retorna uma lista de moradores.
     */
    public List<Morador> listar();

    /**
     *
     * @param morador utilizado para alterar um morador.
     */
    public void alterar(Morador morador);

}
