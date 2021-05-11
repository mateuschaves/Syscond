package negocios;

import dao.*;
import pojos.Morador;
import pojos.Fornecedor;

import java.util.List;

/**
 * Nessa classe de FornecedorNegocios sera chamada a FornecedorDAO para fazer um CRUD que tenha relacao com um BD.
 */
public class FornecedorNegocios {

    private FornecedorDaoInterface fornecedorDao = new FornecedorDAO();

    /**
     *
     * @param fornecedor eh o parametro utilizado para cadastrar um fornecedor.
     */
    public void cadastrar(Fornecedor fornecedor) {

        try {
            fornecedorDao.adicionar(fornecedor);

        } catch (Exception e) {
            System.out.println("FornecedorNegocios: Erro: " + e.getMessage());
        }
    }

    /**
     *
     * @param fornecedor eh o parametro utilizado para deletar um fornecedor.
     */
    public void deletar(Fornecedor fornecedor) {
        try {
            fornecedorDao.remover(fornecedor);
        } catch (Exception e) {
            System.out.println("FornecedorNegocios : Erro: " + e.getMessage());
        }
    }

    /**
     *
     * @return retorna uma lista de fornecedores.
     */
    public List<Fornecedor> listarFornecedores() {
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
            return listaFornecedores;
        } catch (Exception e) {
            System.out.println("FornecedorNegocios: Erro: " + e.getMessage());
        }
            return listaFornecedores;
    }

    /**
     *
     * @param fornecedor eh o parametro utilizado para modificar um fornecedor.
     */
    public void alterar(Fornecedor fornecedor) {

        try {
            fornecedorDao.alterar(fornecedor);
        } catch (Exception e) {
            System.out.println("Fornecedor Negócios: Erro: " + e.getMessage());
        }
    }

    /**
     *
     * @param fornecedor eh o parametro utilizado para procurar um fornecedor, quando acha
     * @return retorna o fornecedor pesquisado.
     */
    public Fornecedor pesquisar(Fornecedor fornecedor) {
            return (fornecedorDao.procurar(fornecedor));
    }
}