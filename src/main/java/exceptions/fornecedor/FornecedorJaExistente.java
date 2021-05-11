package exceptions.fornecedor;

/**
 *  Exception criada caso um fornecedor ja exista.
 */

public class FornecedorJaExistente extends Exception{

	private static final long serialVersionUID = 1L;
	
	public FornecedorJaExistente(String cnpj) {
		super(cnpj);
	}
	
}
