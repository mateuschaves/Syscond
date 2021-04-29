package negocios;

import dao.*;
import pojos.Morador;
import pojos.Fornecedor;

import java.util.List;

public class FornecedorNegocios {

    private FornecedorDaoInterface fornecedorDao = new FornecedorDAO();

    public void cadastrar(Fornecedor fornecedor) {

        try {
            fornecedorDao.adicionar(fornecedor);

        } catch (Exception e) {
            System.out.println("FornecedorNegocios: Erro: " + e.getMessage());
        }
    }

    public void deletar(Fornecedor fornecedor) {
        try {
            fornecedorDao.remover(fornecedor);
        } catch (Exception e) {
            System.out.println("FornecedorNegocios : Erro: " + e.getMessage());
        }
    }

    public void listarFornecedores() {
        int index = 0;
        List<Fornecedor> listaFornecedores = null;

        try {
            listaFornecedores = fornecedorDao.listar();
            System.out.println("Listando Fornecedores de sistemas: ");
            for (Fornecedor a : listaFornecedores) {
                index++;
                System.out.println("");
                System.out.println(index + "º Fornecedor:");
                System.out.println("Nome: " + a.getNome());
                System.out.println("CNPJ: " + a.getCnpj());
                System.out.println("Telefone: " + a.getTelefone());

            }
        } catch (Exception e) {
            System.out.println("FornecedorNegocios: Erro: " + e.getMessage());
        }

    }

    public void alterar(Fornecedor fornecedor) {

        try {
            fornecedorDao.alterar(fornecedor);
        } catch (Exception e) {
            System.out.println("Fornecedor Negócios: Erro: " + e.getMessage());
        }
    }

    public Fornecedor pesquisar(Fornecedor fornecedor) {
            return (fornecedorDao.procurar(fornecedor));
    }
}