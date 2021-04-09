/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;


import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Mattskywalker
 */

@Entity
@Table(name = "morador",schema = "syscond")
public class Morador {

    @Id
    private String cpf;
    @Column
    private String nomeMorador;
    @ManyToOne
    private Apartamento apartamento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario")
    private List<Carro> carros;

    @OneToMany(mappedBy = "MoradorResponsavel")
    private List<Visitante> visitantesList;

    public Morador() {
    }

    public Morador(String cpf) {
        this.cpf = cpf;
    }

    public Morador(String cpf, String nome, Apartamento apartamento, List<Carro> carros, List<Visitante> visitantesList) {
        this.cpf = cpf;
        this.nomeMorador = nome;
        this.apartamento = apartamento;
        this.carros = carros;
        this.visitantesList = visitantesList;
    }

    public Morador(String cpf, List<Carro> carros) {
        this.cpf = cpf;
        this.carros = carros;
    }

    public Morador(String cpf, String nome, Apartamento apartamento, List<Carro> carros) {
        this.cpf = cpf;
        this.nomeMorador = nome;
        this.apartamento = apartamento;
        this.carros = carros;
    }

    public Morador(String cpf, String nome, Apartamento apartamento) {
        this.cpf = cpf;
        this.nomeMorador = nome;
        this.apartamento = apartamento;
    }

    public Morador(String cpf, String nome, List<Carro> carros) {
        this.cpf = cpf;
        this.nomeMorador = nome;
        this.carros = carros;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nomeMorador;
    }

    public void setNome(String nome) {
        this.nomeMorador = nome;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    @JoinColumn(name = "apartamentoMorador")
    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public List<Visitante> getVisitantesList() {
        return visitantesList;
    }

    public void setVisitantesList(List<Visitante> visitantesList) {
        this.visitantesList = visitantesList;
    }
}