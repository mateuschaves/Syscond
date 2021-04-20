package exceptions.funcionario;

/**
 *  @author grupo Sith;
 *  exception criada caso um funcionario nao seja encontrado.
 */

public class FuncionarioNaoEncontrado extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	public FuncionarioNaoEncontrado(String mensagem) {
		super(mensagem);
	}
}
