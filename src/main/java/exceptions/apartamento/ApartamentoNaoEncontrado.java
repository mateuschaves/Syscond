package exceptions.apartamento;

/**
 *  Exception criada caso um apartamento nao seja encontrado.
 */

public class ApartamentoNaoEncontrado extends Exception{

    private static final long serialVersionUID = 1L;

    public ApartamentoNaoEncontrado(int numero) {
        super(Integer.toString(numero));
    }
}
