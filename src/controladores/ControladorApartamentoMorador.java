/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import classeauxiliar.TipoMorador;
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
    
    
    public void CadastrarMorador(String nome, String cpf, int numero, TipoMorador status){
        if(this.apartamentos.procurar(numero) == null){// primeiro tramento, cadastrar morador em apartamento que não existe
           System.out.println("Controler: O apartamento que você tentou atribuir ao morador: "+ nome+ " não existe, Cadastro não realizado");
        }
        else if(this.apartamentos.procurarStatus(numero) == null){// VERIFICA SE O CADASTRO DE PROPRIETARIO TA LIVRE
            //pronto para registrar um proprietario;
            System.out.println("Controler: O apartamento numero: "+ numero +" está disponivel");
            this.moradores.adicionar(nome, cpf, this.apartamentos.procurar(numero),TipoMorador.PROPIETARIO);
            System.out.println("Controler: O Morador: "+ nome +  " foi cadasrtrado");
            System.out.println("Controler: Status: Proprietario");
            this.apartamentos.procurar(numero).getMorador().add(this.moradores.procurar(cpf));//atribui morador ao apartamento
            System.out.println("Controler: O morador: " + nome + " Foi atribuido ao apartamento numero: "+ numero);
            
        }
        else{
            
            System.out.println("Controler: O apartamento numero: "+ numero +" está disponivel apenas para Dependentes");
            this.moradores.adicionar(nome, cpf, this.apartamentos.procurar(numero),TipoMorador.DEPENDENTE);
            System.out.println("Controler: O Morador: "+ nome +  " foi cadasrtrado");
            System.out.println("Controler: Status: Dependente");
            this.apartamentos.procurar(numero).getMorador().add(this.moradores.procurar(cpf));//atribui morador ao apartamento
            System.out.println("Controler: O morador: " + nome + " Foi atribuido ao apartamento numero: "+ numero);
            
            
            //System.out.println("Controler: O apartamento numero : "+numero+ " Já está ocupado pelo morador: " + this.apartamentos.procurar(numero).getMorador().getNome()+ " Cadastro não realizado!");
        }
          
    }
    
    public void deletarMorador(String cpf){
        
        if(this.moradores.procurar(cpf) == null){
            
            System.out.println("Controler: Usuario a ser deletado não existe no sistema");
        }else{
            
            this.apartamentos.removerMorador(this.moradores.procurar(cpf),this.moradores.procurar(cpf).getApartamento().getNumero());
        
            this.moradores.deletar(cpf); 
        }
        
        
        
    }
    
    
    
}
