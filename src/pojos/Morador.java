/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import classeauxiliar.TipoMorador;
import java.util.ArrayList;

/**
 *
 * @author Mattskywalker
 */
public class Morador {
    
    private String nome;
    private String cpf;
    private Apartamento apartamento;
    //private ArrayList<MoradorDependente> dependentes;
    private ArrayList<Visitantes> visitantes;
    private ArrayList<Carro> carros;
    private TipoMorador status;

    public Morador(String nome, String cpf, Apartamento apartamento,TipoMorador status) {
        this.nome = nome;
        this.cpf = cpf;
        this.apartamento = apartamento;
        this.status = status;
    }

    public ArrayList<Carro> getCarros() {
        return carros;
    }

    public void setCarros(ArrayList<Carro> carros) {
        this.carros = carros;
    }

    public TipoMorador getStatus() {
        return status;
    }

    public void setStatus(TipoMorador status) {
        this.status = status;
    }
    
    

    public ArrayList<Visitantes> getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(ArrayList<Visitantes> visitantes) {
        this.visitantes = visitantes;
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
