package exceptions.funcionario;

public class FuncionarioNãoEncontrado extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	public FuncionarioNãoEncontrado(String mensagem) {
		super(mensagem);
	}
}
