/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import negocios.ApartamentoRepositorio;
import negocios.MoradorRepositorio;
import pojos.Apartamento;
import pojos.Morador;

/**
 *
 * @author Mattskywalker
 */
public class ControladorApartamentoMorador {
    
    private ApartamentoRepositorio apartamentos = new ApartamentoRepositorio();
    private MoradorRepositorio moradores = new MoradorRepositorio();
    
    
    private void CadastrarApartamento(int numero, String andar, String bloco, Morador morador){
        this.apartamentos.adicionar(numero, andar, bloco, morador);
        System.out.println("Controler: Cadastro de apartamento numero "+ numero + " concluido!");
        System.out.println("Morador: " + morador.getNome());
        
    }
    
    public void CadastrarApartamento(int numero, String andar, String bloco){
        
        this.apartamentos.adicionar(numero, andar, bloco, null);
        System.out.println("Controler: Cadastro de apartamento numero "+ numero+ " concluido");
    }
    
    
    public void CadastrarMorador(String nome, String cpf, int numero){
        if(this.apartamentos.procurar(numero) == null){
           System.out.println("Controler: O apartamento que você tentou atribuir ao morador: "+ nome+ " não existe, Cadastro não realizado");
        }
        else if(this.apartamentos.procurar(numero).getMorador() == null){
            
            System.out.println("Controler: O apartamento numero: "+ numero +" está disponivel");
            this.moradores.adicionar(nome, cpf, this.apartamentos.procurar(numero));
            System.out.println("Controler: O Morador: "+ nome +  " foi cadasrtrado");
            this.apartamentos.procurar(numero).setMorador(this.moradores.procurar(cpf));//atribui morador ao apartamento
            System.out.println("Controler: O morador: " + nome + " Foi atribuido ao apartamento numero: "+ numero);
            
            
        }
        else{
            
            System.out.println("Controler: O apartamento numero : "+numero+ " Já está ocupado pelo morador: " + this.apartamentos.procurar(numero).getMorador().getNome()+ " Cadastro não realizado!");
        }
            
    }
    
    
    
    
}
