/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classeauxiliar.Conta;
import classeauxiliar.TipoConta;
import static classeauxiliar.TipoConta.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mattskywalker
 */
public class Financeiro {

    // contas a pagar( LUZ agua, produto) e contas a receber;
    
    private ArrayList<Conta> todasContas = new ArrayList<>();
    //private ArrayList<Conta> apagar = new ArrayList<>();
    
    private Conta Procurar(String codigo){
    
        Conta a = null;
        for(int i = 0; i< this.todasContas.size(); i++){
            if(this.todasContas.get(i).getCodigo().equals(codigo)){
                a = this.todasContas.get(i);
                break; 
            } 
        }
        if(a == null){
            System.out.println("Conta nao encontrada");
        }
        
        return a;
    
    }
    
    
    public void deletar(String codigo){
        if(this.Procurar(codigo).getCodigo() == null){
            
            System.out.println("Conta inexistente!");
        }
        else{
            Conta aux = this.Procurar(codigo);
            this.todasContas.remove(this.Procurar(codigo));
            System.out.println("");
            System.out.println(aux.getDescricao() + " Deletada");
            //espaço para registrar pagamento
            
        }

    }
    
    public void pagarConta(String codigo){ // experimental, no momento esse metodo é de baixa imortancia;
        Conta aux  = this.Procurar(codigo);
        
        if(aux == null){// verifica se a conta existe
            System.out.println("Conta a ser paga não encontrada!");
        }
        else{
            //se chegou aqui a conta existe no sistema;
            
            //Registra no historico de pagamento:
            this.deletar(codigo);
            
        }
        
    }
    
    public void ListarContas(){//Printa todas as contas existentes;
        
        
            // mostra todas as contas existentes
            System.out.println("");
            System.out.println("Relatorio todas as contas existentes");
            
            for(Conta conta : this.todasContas){
                System.out.println("");
                System.out.println("Descrição da conta: "+  conta.getDescricao());
                System.out.println("Codigo: " + conta.getCodigo());
                System.out.println("Valor: " + conta.getValor());
                System.out.println("Data de Criação: " + conta.getDataInicial());
                System.out.println("Data de vencimento: "+conta.getDataVencimento());
                System.out.print("Conta à: ");
                System.out.print(conta.getTipoConta() == TipoConta.PAGAR? "PAGAR" : "RECEBER");
                System.out.println(" ");
                
                
            }
            
        
        
    }
    
    
    public void ListarContas(TipoConta tipo){
      
        Conta conta;
        System.out.println("");
        System.out.print("Listando Contas à ");
        System.out.println(tipo == TipoConta.PAGAR? "Pagar": "Receber");
        
            for(int i = 0;i < this.todasContas.size();i++){
                
                conta = this.todasContas.get(i);
                
                if(conta.getTipoConta() == tipo){
                    
                    System.out.println("Descrição da conta: "+  conta.getDescricao());
                    System.out.println("Codigo: " + conta.getCodigo());
                    System.out.println("Valor: " + conta.getValor());
                    System.out.println("Data de Criação: " + conta.getDataInicial());
                    System.out.println("Data de vencimento: "+conta.getDataVencimento());
                    System.out.print("Conta à: ");
                    System.out.println(conta.getTipoConta() == TipoConta.PAGAR? " PAGAR" : " RECEBER");
                    System.out.println("");
                    
                    
                }
            }
        
    }
    
    //void Procurar(String codigo); // pesquisa conta especifica ;
    
    public void registrarConta(String descricao, Date dataVencimento, double valor, String codigo,TipoConta tipoConta){
        
        if(tipoConta == PAGAR){
            // TEMOS UMA CONTA A PAGAR;
            Conta a = new Conta(descricao, dataVencimento,valor,codigo,tipoConta);
            this.todasContas.add(a);
            System.out.println("Descrição: "+ a.getDescricao());
            System.out.println("Conta a Pagar, Registrada com Sucesso!");
        }
        else{
            //temos uma a Receber;
            //será implementado no futuro
            Conta a = new Conta(descricao, dataVencimento,valor, codigo,tipoConta);
            this.todasContas.add(a);
            
            System.out.println("Descrição: "+ a.getDescricao());
            System.out.println("Conta a receber, Registrada com Sucesso!");
            
        }
        
    }
    
    
    
    
}
