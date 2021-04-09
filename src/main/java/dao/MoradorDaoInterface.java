package dao;


import pojos.Morador;

import java.util.List;

public interface MoradorDaoInterface {

    public Morador procurar(String cpf);
    public void adicionar(Morador morador);
    public void remover(Morador morador);
    public List<Morador> listar();
    public void alterar(Morador morador);

}
