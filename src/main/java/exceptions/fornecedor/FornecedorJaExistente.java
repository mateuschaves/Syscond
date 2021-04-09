package exceptions.fornecedor;

public class FornecedorJaExistente extends Exception{

	private static final long serialVersionUID = 1L;
	
	public FornecedorJaExistente(String cnpj) {
		super(cnpj);
	}
	
}
