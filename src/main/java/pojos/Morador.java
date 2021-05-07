/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;


import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 *  @author Mateus Martins
 *  @author Breno Araujo
 *  @author Eduardo Marinho
 *  @author Mateus Henrique;
 *  descricao:
 *  pojo de morador com seus atributos mapeados, entidades, colouna, chave primaria;
 *  com relacionamento com apartamento, varios moradores para um apartamento (n para 1);
 *  com relacionamento com carros, varios carros  para um morador (n para 1);
 *  com relacionamento com visitante, varios visitantes para um morador (n para 1);
 *  Alem de seus construtores gets e sets;
 *  nomeMorador deve ser obrigatorio e deve aceitar apenas letras;
 *  cpf obrigatorio, unico e no formato: 999.999.999-99;
 *  apartamento, os moradores devem estar associados aos seus respectivos apartamentos (obrigatorio),
 *  podendo ter uma quantidade ilimitada de moradores no apartamento;
 *  visitantesList, todos os moradores podem ter uma quantidade ilimitada de visitantes associados;
 *  carros, todos os moradores podem ter uma quantidade ilimitada de carros cadastrados associados.
 */

@Entity
@Table(name = "morador",schema = "syscond")
public class Morador {

    @Id
    @NotNull
    @NotEmpty
    @Column
    private String cpf;
    @Column
    @NotNull
    @Pattern(message = "Entrada invalida!, Apenas Letras são permitidas", regexp = "[a-zA-Z\\wÀ-ú'çÇ ]+")
    private String nomeMorador;
    @ManyToOne
    @NotNull
    private Apartamento apartamento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario")

    private List<Carro> carros;

    @OneToMany(mappedBy = "MoradorResponsavel")

    private List<Visitante> visitantesList;

    public Morador() {
    }

    public Morador(String nomeMorador, Apartamento apartamento) {
        this.nomeMorador = nomeMorador;
        this.apartamento = apartamento;
    }

    /**
     * @param cpf eh usado nesse segundo construtor de morador, pois o primeiro eh vazio.
     */
    public Morador(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @param cpf ,
     * @param nome ,
     * @param apartamento ,
     * @param carros e
     * @param visitantesList sao os parametros usados nesse terceiro construtor de morador.
     */
    public Morador(String cpf, String nome, Apartamento apartamento, List<Carro> carros, List<Visitante> visitantesList) {
        this.cpf = cpf;
        this.nomeMorador = nome;
        this.apartamento = apartamento;
        this.carros = carros;
        this.visitantesList = visitantesList;
    }

    /**
     * @param cpf e
     * @param carros sao os parametros usados nesse quarto construtor de morador.
     */
    public Morador(String cpf, List<Carro> carros) {
        this.cpf = cpf;
        this.carros = carros;
    }

    /**
     * @param cpf ,
     * @param nome ,
     * @param apartamento e
     * @param carros  sao os parametros usados nesse quinto construtor de morador.
     */
    public Morador(String cpf, String nome, Apartamento apartamento, List<Carro> carros) {
        this.cpf = cpf;
        this.nomeMorador = nome;
        this.apartamento = apartamento;
        this.carros = carros;
    }

    /**
     * @param cpf ,
     * @param nome e
     * @param apartamento sao os parametros usados nesse sexto construtor de morador.
     */
    public Morador(String cpf, String nome, Apartamento apartamento) {
        this.cpf = cpf;
        this.nomeMorador = nome;
        this.apartamento = apartamento;
    }

    /**
     * @param cpf ,
     * @param nome e
     * @param carros sao os parametros usados nesse setimo construtor de morador.
     */
    public Morador(String cpf, String nome, List<Carro> carros) {
        this.cpf = cpf;
        this.nomeMorador = nome;
        this.carros = carros;
    }
    /**
     * @return retorna o cpf do morador.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf eh atribuido um novo cpf para o morador.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return retorna o nome do morador.
     */
    public String getNome() {
        return nomeMorador;
    }

    /**
     * @param nome eh atribuido um novo nome para o morador.
     */
    public void setNome(String nome) {
        this.nomeMorador = nome;
    }

    /**
     * @return retorna a lista de carros associadas ao morador.
     */
    public List<Carro> getCarros() {
        return carros;
    }

    /**
     * @param carros eh atribuido uma nova lista de carros para o morador.
     */
    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    /**
     * @return retorna o apartamento do morador.
     */
    @JoinColumn(name = "apartamentoMorador")
    public Apartamento getApartamento() {
        return apartamento;
    }

    /**
     * @param apartamento eh atribuido um novo apartamento para o morador.
     */
    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    /**
     * @return retorna uma lista de visitantes do morador.
     */
    public List<Visitante> getVisitantesList() {
        return visitantesList;
    }

    /**
     * @param visitantesList eh atribuido uma nova lista de visitantes para o morador.
     */
    public void setVisitantesList(List<Visitante> visitantesList) {
        this.visitantesList = visitantesList;
    }
}