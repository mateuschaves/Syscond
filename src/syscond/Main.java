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

//import controladores.ControladorApartamentoMorador;
import java.util.Date;
import negocios.ApartamentoRepositorio;
import negocios.Financeiro;
import negocios.FornecedorRepositorio;
import negocios.FuncionarioRepositorio;
import negocios.MoradorRepositorio;
import pojos.Apartamento;
import pojos.Fornecedor;
import pojos.Morador;

       
/**
 *
 * @author Mattskywalker
 */
public class Main {
    
    public static void main(String[] arg) throws ApartamentoJaExistente, ApartamentoNaoEncontrado{
        
        FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();
        ApartamentoRepositorio apartamentos = new ApartamentoRepositorio();
        
        
        fornecedorRepositorio.cadastrar("Breno", "1234567890", "81 91992517");
        fornecedorRepositorio.cadastrar("brenda", "123456789", "819192517");
        fornecedorRepositorio.procurarFornecedor("1234567890");
        //cadastro de fornecedor funcionando;
        fornecedorRepositorio.deletar("1234567890");
        System.out.println("Depois de deletar");
        fornecedorRepositorio.procurarFornecedor("1234567890");
        
        fornecedorRepositorio.alterarNome("123456789", "Mateus");
        fornecedorRepositorio.procurarFornecedor("123456789");
        
        fornecedorRepositorio.alterarTelefone("123456789", "321654987");
        
        
        
        //abaixo teste de cadastro de contas;
        
        
        
        Financeiro contabilidade = new Financeiro(); 
        
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
        
        FuncionarioRepositorio funcionarios = new FuncionarioRepositorio();
        
        funcionarios.cadastrar("Roger", "123", "Zelador");
        funcionarios.cadastrar("Breno", "456", "Porteiro");
        funcionarios.cadastrar("Madara", "1313", "SEI LA");
        
        funcionarios.deletar("456");
        funcionarios.alterarNome("123", "Rodoado");
        funcionarios.procurarFuncionario("12312312312");
        
        funcionarios.deletar("123");
        funcionarios.alterarFuncao("1313", "Gerente geral");
        funcionarios.procurarFuncionario("123");
        
        //
                
        
        
        
        
        
       
    }
    
}
