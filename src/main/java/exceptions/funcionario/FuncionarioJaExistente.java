package exceptions.funcionario;

/**
 *  @author grupo Sith;
 *  exception criada caso um funcionario ja exista.
 */

public class FuncionarioJaExistente extends Exception{

    private static final long serialVersionUID = 1L;

    public FuncionarioJaExistente(String cpf){
        super(cpf);
    }
}
