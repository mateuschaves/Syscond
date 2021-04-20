package exceptions.apartamento;

/**
 * @author grupo Sith;
 *  exception criada caso um apartamento ja exista.
 */

public class ApartamentoJaExistente extends Exception{

    private static final long serialVersionUID = 1L;

    public ApartamentoJaExistente(int numero) {
        super(Integer.toString(numero));
    }
}
