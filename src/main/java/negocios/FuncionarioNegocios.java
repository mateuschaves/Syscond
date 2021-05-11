package negocios;

import dao.FuncionarioDAO;
import dao.FuncionarioDaoInterface;
import pojos.Funcionario;

import java.util.List;

/**
 * Nessa classe de FuncionarioNegocios sera chamada a FuncionarioDAO para fazer um CRUD que tenha relacao com um BD.
 */
public class FuncionarioNegocios {

    FuncionarioDaoInterface funcionarioDao = new FuncionarioDAO();

    /**
     *
     * @param funcionario eh o parametro utilizado para cadastrar um funcionario.
     */
    public void cadastrar(Funcionario funcionario){

        try{
            funcionarioDao.adicionar(funcionario);
        }catch (Exception e){
            //e.printStackTrace();
        }
    }

    /**
     *
     * @param funcionario eh o parametro utilizado para apagar um funcionario.
     */
    public void deletar(Funcionario funcionario){
        try{
            funcionarioDao.remover(funcionario);
        }catch(Exception e){
            //e.printStackTrace();
        }
    }

    /**
     *
     * @return retorna uma lista de funcionarios.
     */
    public List<Funcionario> listarFuncionarios(){
        int index = 0;
        List<Funcionario> listaFuncionarios = null;

        try{
            listaFuncionarios = funcionarioDao.listar();
            System.out.println("Listando Funcionaros: ");
            for (Funcionario a:listaFuncionarios) {
                index++;
                System.out.println("");
                System.out.println(index + "º Funcionario:");
                System.out.println("CPF: " + a.getCpf());
                System.out.println("Nome: " + a.getNome());
                System.out.println("Função: " + a.getFuncao());
            }
            return listaFuncionarios;
        }catch(Exception e){
            System.out.println("Funcionario Negocios: Erro: " + e.getMessage());
        }
        return listaFuncionarios;
    }

    /**
     *
     * @param funcionario eh o parametro utilizado para encontrar um apartamento e
     * @param cpf eh o usado para colocar um novo cpf nesse funcionario.
     */
    public void alterarID(Funcionario funcionario, String cpf){
        Funcionario target = null;
        try{
            target = funcionarioDao.procurar(funcionario);
            funcionarioDao.remover(target);
            target.setCpf(cpf);
            funcionarioDao.adicionar(target);

        }catch (Exception e){
            System.out.println("Funcionario Negocios: Erro: " + e.getMessage());
        }

    }

    /**
     *
     * @param funcionario eh o parametro utilizado para alterar um funcionario.
     */
    public void alterar(Funcionario funcionario){

        try {
            funcionarioDao.alterar(funcionario);
        }catch (Exception e){
            System.out.println("Funcionario Negócios: Erro: " + e.getMessage());
        }
    }

    /**
     *
     * @param funcionario eh o parametro utilizado para procurar um funcionario, quando encontra
     * @return retorna um funcionario pesquisado.
     */
    public Funcionario pesquisar(Funcionario funcionario){

        return(funcionarioDao.procurar(funcionario));

    }
}
