/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * descricao:
 * pojo de apartamaneto com seus atributos mapeados, entidades, colounas, chave primaria;
 * com relacionamento com morador, um apartamento para varios moradores (1 para n);
 * Alem de seus construtores gets e sets;
 * numero eh obrigatorio, unico em cada bloco e deve aceitar apenas numeros;
 * andar e bloco sao obrigatorios;
 * moradores pode ter moradores associados ou nao, pois o cadastro de apartamento independe da existencia
 * de moradores.
 */

@Entity
@Table(name = "apartamento",schema = "syscond")
public class Apartamento {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "O numero do apartamento não pode ser nulo")
    @Pattern(message="Entrada invalida!, São permitidos apenas numeros!", regexp = "[0-9^]+")
    private String numero;
    @Column
    @NotNull(message = "O andar não pode ser nulo")
    @Pattern(message="Entrada invalida!, São permitidos apenas numeros!", regexp = "[0-9]*")
    private String andar;
    @Column
    @NotNull(message = "O bloco não pode ser nulo")
    @Pattern(message="Entrada invalida!, São permitidos apenas letras!", regexp = "[0-9a-zA-Z çÇ]*")
    private String bloco;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "apartamento")
    private List<Morador> moradores = new ArrayList<>();

    public Apartamento() {

    }

    /**
     * @param numero eh o parametro usado nesse segundo construtor de apartamento, pois o primeiro eh vazio.
     */
    public Apartamento(String numero) {
        this.numero = numero;
    }

    /**
     * @param numero e
     * @param moradores sao os parametros usados nesse terceiro construtor de apartamento.
     */
    public Apartamento(String numero, List<Morador> moradores) {
        this.numero = numero;
        this.moradores = moradores;
    }

    /**
     * @param numero ,
     * @param andar e
     * @param bloco sao os parametros usados nesse quarto construtor de apartamento.
     */
    public Apartamento(String numero, String andar, String bloco) {
        this.numero = numero;
        this.andar = andar;
        this.bloco = bloco;
    }

    /**
     * @param numero ,
     * @param andar ,
     * @param bloco e
     * @param moradores sao os parametros usados nesse quinto construtor de apartamento.
     */
    public Apartamento(String numero, String andar, String bloco, List<Morador> moradores) {
        this.numero = numero;
        this.andar = andar;
        this.bloco = bloco;
        this.moradores = moradores;
    }

    /**
     * @param numero ,
     * @param andar ,
     */

    public Apartamento(String numero, String andar) {
        this.numero = numero;
        this.andar = andar;
    }

    /**
     * @return retorna o numero do apartamento.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero eh atribuido um novo numero para o apartamento.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return retorna o andar do apartamento.
     */
    public String getAndar() {
        return andar;
    }

    /**
     * @param andar eh atribuido um novo andar para o apartamento.
     */
    public void setAndar(String andar) {
        this.andar = andar;
    }

    /**
     * @return retorna o bloco do apartamento.
     */
    public String getBloco() {
        return bloco;
    }

    /**
     * @param bloco eh atribuido um novo bloco para o apartamento.
     */
    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    /**
     * @return retorna a lista de moradores do apartamento.
     */
    public List<Morador> getMorador() {
        return moradores;
    }

    /**
     * @param morador eh atribuido uma nova lista de moradores para o apartamento.
     */
    public void setMorador(List<Morador> morador) {
        this.moradores = morador;
    }
}
