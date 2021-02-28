package interfacedecodigo;

import java.util.ArrayList;

import pojos.Apartamento;
import pojos.Morador;

public interface ApartamentoRepositorioInterface {
    public void adicionar(int numero, String andar, String bloco, Morador morador);

    public void remover(Morador morador, int numero);

    public ArrayList<Apartamento> listar();

    public void alterar(Apartamento apartamento);

    public Apartamento procurar(int id);
}
