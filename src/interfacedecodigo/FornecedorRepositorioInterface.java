package interfacedecodigo;

import java.util.ArrayList;

import pojos.Fornecedor;

public interface FornecedorRepositorioInterface {
	
	public Fornecedor procurar(String id);
	public Fornecedor procurar(Fornecedor fornecedor);
    public void adicionar(Fornecedor fornecedor);
    public void remover(Fornecedor fornecedor);
    public ArrayList<Fornecedor> listar();
    public void alterar(Fornecedor fornecedor);
	
}
