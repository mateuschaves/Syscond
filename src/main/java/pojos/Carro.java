/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 *   @author Mateus Martins
 *   @author Breno Araujo
 *   @author Eduardo Marinho
 *   @author Mateus Henrique;
 *   descricao:
 *   pojo de carro com seus atributos mapeados, entidades, colouna, chave primaria;
 *   com relacionamento com morador, varios carros para um morador (n para 1);
 *   Alem de seus construtores gets e sets;
 *   placa deve ser obrigatoria, unica e no formato: ABC-1234;
 *   modelo deve ser obrigatorio;
 *   cor deve ser obrigatoria e deve aceitar apenas letras;
 *   proprietario precisa ter um morador associado ao respectivo carro.
 */

@Entity
@Table(name = "carro",schema = "syscond")
public class Carro {

    @Id
    @NotNull(message = "a placa nao pode ser nula!")
    @Pattern(message="Entrada invalida!, deve ser ultilizado um formato de palca de carro",
             regexp = "^(([A-Z]{3}[-]{1}[0-9]{4})|([A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}))$")
    private String placa;
    @Column
    @NotNull(message = "o modelo é obrigatorio!")
    @Pattern(message = "Entrada invalida!, Caracteres especiais nao sao permitidos!", regexp = "[A-Za-z0-9 ]+")
    private String modelo;
    @Column
    @NotNull(message = "a cor é obrigatoria!")
    @Pattern(message = "Entrada invalida! Apenas Letras!", regexp = "[A-Za-z ]+[ç]*[Ç]*")
    private String cor;
    @ManyToOne
    @NotNull(message = "um carro precisar ter um dono!")
    private Morador proprietario;

    public Carro() {
    }

    /**
     * @param placa ,
     * @param modelo e
     * @param cor sao os parametros usados nesse segundo construtor de carro pois o primeiro construtor eh vazio.
     */
    public Carro(String placa, String modelo, String cor) {
        this.placa = placa;
        this.cor = cor;
        this.modelo = modelo;

    }

    /**
     * @param placa ,
     * @param modelo ,
     * @param cor e
     * @param proprietario sao os parametros usados nesse terceiro construtor de carro.
     */
    public Carro(String placa, String modelo, String cor, Morador proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.proprietario = proprietario;
    }

    /**
     *
     * @param placa é o parametro usado nesse quarto construtor de carro.
     */
    public Carro(String placa){
        this.placa = placa;
    }

    /**
     * @return retorna a placa do carro.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa eh atribuido uma nova placa para o carro.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return retorna o modelo do carro.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo eh atribuido um novo modelo para o carro.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return retorna a cor do carro.
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor eh atribuido uma nova cor para o carro.
     */
    public void setCor(String cor) {
        this.cor = cor;
    }


    /**
     * @return retorna o proprietario do carro.
     */
    @JoinColumn(name = "proprietarioCpf")
    public Morador getProprietario() {
        return proprietario;
    }

    /**
     * @param proprietario eh atribuido um novo morador para o carro.
     */
    public void setProprietario(Morador proprietario) {
        this.proprietario = proprietario;
    }
}
