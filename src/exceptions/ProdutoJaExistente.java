package exceptions;

public class ProdutoJaExistente extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ProdutoJaExistente(String mensagem) {
        super(mensagem);
    }
}
