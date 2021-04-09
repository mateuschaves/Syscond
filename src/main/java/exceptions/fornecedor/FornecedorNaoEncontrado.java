package exceptions.fornecedor;

public class FornecedorNaoEncontrado extends Exception{

	private static final long serialVersionUID = 1L;
	
	public FornecedorNaoEncontrado(String cnpj) {
		super(cnpj);
	}
	
}
