/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syscond;
import classeauxiliar.TipoConta;
import exceptions.apartamento.ApartamentoJaExistente;
import exceptions.apartamento.ApartamentoNaoEncontrado;
import exceptions.fornecedor.FornecedorNãoEncontrado;
import exceptions.funcionario.FuncionarioNãoEncontrado;
import interfacedecodigo.*;


//import controladores.ControladorApartamentoMorador;
import java.util.Date;
import negocios.*;
import pojos.*;
import repositorios.*;

       
/**
 *
 * @author Mattskywalker
 */
public class Main {
    
    public static void main(String[] arg) throws ApartamentoJaExistente, ApartamentoNaoEncontrado, FornecedorNãoEncontrado, FuncionarioNãoEncontrado{
        
        FornecedorRepositorioInterface fornecedores = new FornecedorRepositorio();//interface repositorios de fornecedores;
        ApartamentoRepositorioInterface apartamentos = new ApartamentoRepositorio();//interface repositorio de apartamentos;
        
        Financeiro contabilidade = new Financeiro();
        
        //teste do cadastro de fornecedores;
        
        System.out.println("Fornecedores cadastrados: ");
        System.out.println("");
        
        Fornecedor fornecedor;
        
        fornecedores.adicionar(fornecedor = new Fornecedor("Jaba The Hutt", "1", "180"));
        fornecedores.adicionar(fornecedor = new Fornecedor("Boba Fett", "2", "157"));
        fornecedores.adicionar(fornecedor = new Fornecedor("Han Solo", "3", "171"));
        fornecedores.adicionar(fornecedor = new Fornecedor("Hondo Ohnaka", "4", "190"));
        
        try {
        	System.out.println("Fornecedor encontrado: "  + fornecedores.procurar("0").getNome());
        }
        catch(Exception e){
        	System.out.println("Erro: " + e.getMessage());
        	
        }
        
       
        
        
        System.out.println("Fornecedores:");
        System.out.println("");
        
        fornecedores.alterar(fornecedor = new Fornecedor("Han Solo","3","100"));
        
        for(Fornecedor cadastros : fornecedores.listar()) {
        	
        	System.out.println("Fornecedores cadastrados: " + cadastros.getNome());
        	System.out.println("Fornecedores cadastrados: " + cadastros.getTelefone());
        	System.out.println("");
        }
        
        
        //abaixo teste de cadastro de contas;
        
        
        contabilidade.registrarConta("Caça Tie", new Date("02/30/2021"), 30, "1234", TipoConta.PAGAR);
        contabilidade.registrarConta("Blaster", new Date("10/12/2021"), 50, "4321", TipoConta.PAGAR);
        contabilidade.registrarConta("Sabre de luz", new Date("11/12/2022"), 100, "421", TipoConta.RECEBER);
        contabilidade.registrarConta("Droid de batalha", new Date("1/1/2023"), 50, "41", TipoConta.RECEBER);
        
        contabilidade.ListarContas();
        
        contabilidade.deletar("1234");
        
        contabilidade.ListarContas();
        //positivo e funcional;
        
        System.out.println("");
        System.out.println("Main: Iniciando testes do controlador Apartamento e Morador");
        System.out.println("");
        
        //cadastro de apartamento;
        
        Apartamento ap1 = new Apartamento(1,1,"B");// objetos apartamento criados;
        Apartamento ap2 = new Apartamento(2,1,"B");
        Apartamento ap3 = new Apartamento(3,1,"B");
        Apartamento ap4 = new Apartamento(4,1,"B");
        
        
        apartamentos.adicionar(ap1);//objetos apartamento adicionados no repositorio;
        System.out.println("Apartamento numero: " + ap1.getNumero() + " Cadastrado com sucesso!");
        apartamentos.adicionar(ap2);
        System.out.println("Apartamento numero: " + ap2.getNumero() + " Cadastrado com sucesso!");
        apartamentos.adicionar(ap3);
        System.out.println("Apartamento numero: " + ap3.getNumero() + " Cadastrado com sucesso!");
        apartamentos.adicionar(ap4);
        System.out.println("Apartamento numero: " + ap4.getNumero() + " Cadastrado com sucesso!");
        
        //apartamentos.adicionar(ap4);
        
        
        System.out.println("------------------");//tentativa de alterar um dado de um objeto que não existe;
        try {
        	apartamentos.alterar(5, ap4);
        }
        catch(Exception e){
        	
        	System.out.println(e.getMessage());//print do erro retornado;
        }
        System.out.println("------------------");
        
        apartamentos.remover(ap4);//teste da função remover do repositorio;
        
        for(Apartamento a : apartamentos.listar()){//listagem dos apartamentos existentes no repositorios;
        	
        	System.out.println("Apartamento: "+ a.getNumero());
        }
        
        try {
        	apartamentos.procurar(6);
        }
        catch(Exception e) {
        	
        	System.out.println(e.getMessage());// teste procurar pelo id;
        	
        }
        
        
        //teste repositorio de funcionarios;
        
        System.out.println("");
        System.out.println("Main: Teste do repositorio de funcionarios");
        System.out.println("");
        
        FuncionarioRepositorioInterface funcionarios = new FuncionarioRepositorio();
        Funcionario funcionario;
        
        funcionarios.adicionar(funcionario = new Funcionario("Breno","123123123","Duelista"));
        funcionarios.adicionar(funcionario = new Funcionario("Judeu","123","Senhor"));
        funcionarios.adicionar(funcionario = new Funcionario("Vini","123123","Senhor dos Dragões"));
        funcionarios.adicionar(funcionario = new Funcionario("Mattskywalker","0","Vagabundo"));
        
        try {
        	System.out.println("Funcionario: " + funcionarios.procurar("1000").getNome());
        }
        catch(Exception e) {
        	System.out.println("Erro: " + e.getMessage());
        }
        funcionarios.alterar(funcionario = new Funcionario("Mateus","0","Darth Vader"));
        
        
        for(Funcionario fun : funcionarios.listar()) {//listagem;
        	
        	System.out.println("Lista de Funcionarios Cadastrados: " + fun.getNome());
        	System.out.println("Função: " + fun.getFuncao());
        	System.out.println("");
        	
        }
        
        
        
        
    }
    
}
