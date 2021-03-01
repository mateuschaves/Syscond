package exceptions;

public class ProdutoEmEstoqueCritico extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public ProdutoEmEstoqueCritico(String codigoDeBarras){
        super(codigoDeBarras);
    }
}
