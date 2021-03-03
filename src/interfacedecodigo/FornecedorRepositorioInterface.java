package interfacedecodigo;

import java.util.ArrayList;

import exceptions.fornecedor.FornecedorNãoEncontrado;
import pojos.Fornecedor;

public interface FornecedorRepositorioInterface {
	
	public Fornecedor procurar(String id) throws FornecedorNãoEncontrado;
	public Fornecedor procurar(Fornecedor fornecedor) throws FornecedorNãoEncontrado;
    public void adicionar(Fornecedor fornecedor);
    public void remover(Fornecedor fornecedor);
    public ArrayList<Fornecedor> listar();
    public void alterar(Fornecedor fornecedor) throws FornecedorNãoEncontrado;
	
}
