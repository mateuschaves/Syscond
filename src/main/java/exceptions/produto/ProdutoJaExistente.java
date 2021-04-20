package exceptions.produto;

/**
 *  @author grupo Sith;
 *  exception criada caso um produto ja exista.
 */

public class ProdutoJaExistente extends Exception{

    private static final long serialVersionUID = 1L;

    public ProdutoJaExistente(String mensagem) {
        super(mensagem);
    }
}
