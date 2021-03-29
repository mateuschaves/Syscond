package exceptions.produto;

public class ProdutoNaoEncontrado extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ProdutoNaoEncontrado(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontrado() {
        super();
    }
}
