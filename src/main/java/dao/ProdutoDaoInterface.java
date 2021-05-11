package dao;

import pojos.Produto;

import java.util.List;

/**
 * Interface de produto com suas assinaturas
 */
public interface ProdutoDaoInterface {

    /**
     *
     * @param codigoDeBarras utilizado para procurar um produto;
     * @return retorna um produto.
     */
    public Produto procurar(String codigoDeBarras);

    /**
     *
     * @param produto utilizado para adicionar um produto.
     */
    public void adicionar(Produto produto);

    /**
     *
     * @param produto utilizado para remover um produto.
     */
    public void remover(Produto produto);

    /**
     *
     * @return retorna uma lista de produtos.
     */
    public List<Produto> listar();

    /**
     *
     * @param produto utilizado para alterar um produto.
     */
    public void alterar(Produto produto);

}
