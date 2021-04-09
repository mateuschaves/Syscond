package exceptions.funcionario;

public class FuncionarioJaExistente extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FuncionarioJaExistente(String cpf){
        super(cpf);
    }
}
