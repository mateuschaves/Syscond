package exceptions.funcionario;

public class FuncionarioNaoEncontrado extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	public FuncionarioNaoEncontrado(String mensagem) {
		super(mensagem);
	}
}
