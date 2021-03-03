/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import classeauxiliar.TipoMorador;
import java.util.ArrayList;
import pojos.Apartamento;
import pojos.Morador;

/**
 *
 * @author Mattskywalker
 */
public class MoradorRepositorio {
    
    private ArrayList<Morador> todosMoradores = new ArrayList<Morador>();
    
    public Morador procurar(String cpf){
        
        Morador morador = null;
        
            for(int i = 0;i < this.todosMoradores.size();i++){
            
            if(this.todosMoradores.get(i).getCpf().equals(cpf)){
                
                //System.out.println("Morador encontrado");
                morador = this.todosMoradores.get(i);
                break;
            }
            
        }
            if(morador == null){
               //System.out.println("Morador não encontrado");
            }
            return morador;
    }
    
    public void adicionar(String nome, String cpf, Apartamento apartamento, TipoMorador status){
        
        if(this.procurar(cpf) == null){
            
            
            //System.out.println("pronto para cadastrar");
            Morador aux = new Morador(nome, cpf, apartamento,status);
            this.todosMoradores.add(aux);
            //System.out.println("Repositorio: Morador: "+ nome + "Cadastrado");
        }
        else{
            //System.out.println("Repositorio: Morador : "+ nome + " já foi cadastrado");
        }
        
    }
    
    public void deletar(String cpf){
        
        if(this.procurar(cpf).getCpf().equals(cpf)){
            // encontrado
            this.todosMoradores.remove(this.procurar(cpf));
            
        }
        else{
            System.out.println("Não encontrado para deletar");
        }
    }
    
}
