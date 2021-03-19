/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Mattskywalker
 */
@Entity
public class Funcionario {

    @Id
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String funcao;

    public Funcionario() {
    }

    public Funcionario (String nome, String cpf, String funcao){
           this.nome = nome;
           this.cpf = cpf;
           this.funcao = funcao;
    }
    
    public String getNome(){
           return this.nome;
    }
    
    public void setNome(String nome){
            this.nome = nome;    
    }
    
    public String getCpf(){
            return this.cpf;
    } 
    
    public void setCpf(String cpf){
            this.cpf = cpf;
    }
    
    public String getFuncao(){
            return this.funcao;
    }
    
    public void setFuncao(String funcao){
            this.funcao = funcao;
    }
    
}
