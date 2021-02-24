/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author Mattskywalker
 */
public class Morador {
    
    private String nome;
    private String cpf;
    private Apartamento apartamento;

    public Morador(String nome, String cpf, Apartamento apartamento) {
        this.nome = nome;
        this.cpf = cpf;
        this.apartamento = apartamento;
    }

    
    
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }
    
    
}
