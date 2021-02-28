package interfacedecodigo;

import java.util.ArrayList;
import pojos.Apartamento;

public interface ApartamentoRepositorioInterface {
	
    public void adicionar(Apartamento apartamento);

    public void remover(Apartamento apartamento);

    public ArrayList<Apartamento> listar();

    public void alterar(int numero, Apartamento apartamento) throws Exception;

    public Apartamento procurar(int numero) throws Exception;
    
}
