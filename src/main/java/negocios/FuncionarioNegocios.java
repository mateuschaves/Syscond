package negocios;

import dao.FuncionarioDAO;
import dao.FuncionarioDaoInterface;
import pojos.Funcionario;

import java.util.List;

public class FuncionarioNegocios {

    FuncionarioDaoInterface funcionarioDao = new FuncionarioDAO();

    public void cadastrar(Funcionario funcionario){

        try{
            funcionarioDao.adicionar(funcionario);
        }catch (Exception e){
            //e.printStackTrace();
        }
    }

    public void deletar(Funcionario funcionario){
        try{
            funcionarioDao.remover(funcionario);
        }catch(Exception e){
            //e.printStackTrace();
        }
    }

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

    public void alterar(Funcionario funcionario){

        try {
            funcionarioDao.alterar(funcionario);
        }catch (Exception e){
            System.out.println("Funcionario Negócios: Erro: " + e.getMessage());
        }
    }

    public Funcionario pesquisar(Funcionario funcionario){

        return(funcionarioDao.procurar(funcionario));

    }
}
