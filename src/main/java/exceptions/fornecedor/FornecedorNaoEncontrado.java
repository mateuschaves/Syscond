package exceptions.fornecedor;

/**
 *  @author grupo Sith;
 *  exception criada caso um fornecedor nao seja achado.
 */

public class FornecedorNaoEncontrado extends Exception{

	private static final long serialVersionUID = 1L;
	
	public FornecedorNaoEncontrado(String cnpj) {
		super(cnpj);
	}
	
}
