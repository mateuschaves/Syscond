/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syscond;
import classeauxiliar.TipoConta;
import classeauxiliar.TipoMorador;
import exceptions.ApartamentoJaExistente;
import exceptions.ApartamentoNaoEncontrado;
import interfacedecodigo.FuncionarioRepositorioInterface;


//import controladores.ControladorApartamentoMorador;
import java.util.Date;
import negocios.ApartamentoRepositorio;
import negocios.Financeiro;
import negocios.FornecedorRepositorio;
import negocios.FuncionarioRepositorio;
import negocios.MoradorRepositorio;
import pojos.Apartamento;
import pojos.Fornecedor;
import pojos.Funcionario;
import pojos.Morador;

       
/**
 *
 * @author Mattskywalker
 */
public class Main {
    
    public static void main(String[] arg) throws ApartamentoJaExistente, ApartamentoNaoEncontrado{
        
        FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();//inicia repositorio de fornecedor;
        ApartamentoRepositorio apartamentos = new ApartamentoRepositorio();//inicia repositorio de apartamentos;
        Financeiro contabilidade = new Financeiro();//inicia o financeiro, onde são registradas as contas a pagar e receber;
        
        fornecedorRepositorio.cadastrar("Breno", "1234567890", "81 91992517");//cadastro de fornecedores;
        fornecedorRepositorio.cadastrar("brenda", "123456789", "819192517");
        fornecedorRepositorio.procurarFornecedor("1234567890");
        //cadastro de fornecedor funcionando;
        fornecedorRepositorio.deletar("1234567890");//deletar fornecedores;
        System.out.println("Depois de deletar");
        fornecedorRepositorio.procurarFornecedor("1234567890");//procurar;
        
        fornecedorRepositorio.alterarNome("123456789", "Mateus");//alterar;
        fornecedorRepositorio.procurarFornecedor("123456789");
        
        fornecedorRepositorio.alterarTelefone("123456789", "321654987");
        
        
        
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
        
        
        funcionarios.alterar(funcionario = new Funcionario("Mateus","0","Darth Vader"));
        
        
        for(Funcionario fun : funcionarios.listar()) {//listagem;
        	
        	System.out.println("Lista de Funcionarios Cadastrados: " + fun.getNome());
        	System.out.println("Função: " + fun.getFuncao());
        	System.out.println("");
        	
        }
        
        
        
        
    }
    
}
