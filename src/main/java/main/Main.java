package main;

import dao.JPAUtil;

import exceptions.fornecedor.FornecedorNaoEncontrado;
import exceptions.funcionario.FuncionarioJaExistente;
import exceptions.funcionario.FuncionarioNaoEncontrado;
import pojos.Funcionario;
import dao.FuncionarioDAO;
import dao.FuncionarioDaoInterface;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FornecedorNaoEncontrado, IOException {
        //magical - do not touch
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF); //or whatever level you need
        //magical - do not touch
        
        
        FuncionarioDaoInterface funcionarioDao = new FuncionarioDAO();
        try {
            funcionarioDao.adicionar(new Funcionario("Alexandre", "37455002025", "Contabilidade"));
            funcionarioDao.adicionar(new Funcionario("Fernando", "13975666048", "Estoque"));
            funcionarioDao.adicionar(new Funcionario("Luciana", "88859826039", "Porteiro"));
            System.out.println("Funcionários adicionados com sucesso!");
        } catch (FuncionarioJaExistente e) {
            System.out.println("Funcionário com CPF " + e.getMessage() + " já foi cadastrado no sistema");
        }

        try {
            System.out.println("Oi, eu sou o " + funcionarioDao.procurar("37455002025").getNome());
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("Funcionário com CPF " + e.getMessage() + " não foi encontrado no sistema");
        }

        try {
            for(Funcionario funcionario: funcionarioDao.listar()){
                System.out.println("Nome: " + funcionario.getNome());
            }
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("A lista de funcionários está vazia.");
        }

        try {
            funcionarioDao.remover("37455002025");
            System.out.println("Funcionário removido com sucesso!");
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("Funcionário com CPF " + e.getMessage() + " não foi encontrado no sistema");
        }

        try {
            for(Funcionario funcionario: funcionarioDao.listar()){
                System.out.println("Nome: " + funcionario.getNome());
            }
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("A lista de funcionários está vazia.");
        }

        try {
            funcionarioDao.alterar(new Funcionario("João", "13975666048", "Faxineiro"));
            System.out.println("Funcionário alterado com sucesso!");
        } catch (FuncionarioNaoEncontrado | FuncionarioJaExistente e1) {
            System.out.println("Deu a mulesta");
        }

        try {
            for(Funcionario funcionario: funcionarioDao.listar()){
                System.out.println("Nome: " + funcionario.getNome());
            }
        } catch (FuncionarioNaoEncontrado e) {
            System.out.println("A lista de funcionários está vazia.");
        }

        // Fornecedor fornecedor = new Fornecedor("1234","Breno","indefinido");
        // Fornecedor fornecedor1 = new Fornecedor("12345","Luana","indefinido");

        // FornecedorDaoInterface fornecedorDao = new FornecedorDAO();

        // //fornecedorDao.adicionar(fornecedor);
        // try {
        //     System.out.println("Fornecedor procurado: " + fornecedorDao.procurar("1235").getNome());
        // }catch (NullPointerException e){
        //     System.err.println("ERRO: EITA, PORRA DO CARALHO, AGORA FUDEU, FILHA DA PUTA");
        // }



        // System.out.println("Lista de fornecedores: ");

        // for (Fornecedor a: fornecedorDao.listar()) {

        //     System.out.println("Fornecedor: " + a.getNome());
        // }

        // try {
        //     System.out.println("Fornecedor encontrado: " + fornecedorDao.procurar("123").getNome());
        // } catch (NullPointerException e) {
        //     e.printStackTrace();
        // }
        

        // fornecedorDao.remover(fornecedor);

        // fornecedorDao.alterar(new Fornecedor("12345","Luana Martins de Andrade Lima","indefinido"));

        // System.out.println("Lista de fornecedores: ");
        // for (Fornecedor a: fornecedorDao.listar()) {

        //     System.out.println("Fornecedor: " + a.getNome());
        // }
        
        JPAUtil.close();

    }


}
