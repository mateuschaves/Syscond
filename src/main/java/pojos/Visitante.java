/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.*;

/**
 *  @author Mateus Martins
 *  @author Breno Araujo
 *  @author Eduardo Marinho
 *  @author Mateus Henrique;
 *  descricao:
 *  pojo de visitante com seus atributos mapeados, entidades, colouna, chave primaria;
 *  com relacionamento com morador, varios visitantes para um morador (n para 1);
 *  Alem de seus construtores gets e sets;
 *  cpf obrigatorio, unico e no formato: 999.999.999-99;
 *  nome deve ser obrigatorio e deve aceitar apenas letras;
 *  MoradorResponsavel cada visitante deve estar associado com o morador a ser visitado.
 *
 *
 */

@Entity
public class Visitante {

    @Id
    private String cpf;
    @Column
    private String nome;
    @ManyToOne
    private Morador MoradorResponsavel;

    public Visitante() {
    }

    /**
     * @param cpf ,
     * @param nome e
     * @param MoradorResponsavel sao os parametros usados nesse segundo construtor de visitante, pois o primeiro construtor eh vazio.
     */
    public Visitante(String cpf, String nome, Morador MoradorResponsavel) {
        this.cpf = cpf;
        this.nome = nome;
        this.MoradorResponsavel = MoradorResponsavel;
    }

    /**
     * @return retorna o cpf do visitante.
     */
    public String getCpf() {
        return cpf;
    }


    /**
     * @param cpf eh atribuido um novo cpf para o visitante.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return retorna o nome do visitante.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome eh atribuido um novo nome para o visitante.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return retorna o cpf do morador que sera visitado.
     */
    @JoinColumn(name = "moradorResponsavel")
    public Morador getCpfMoradorResponsavel() {
        return MoradorResponsavel;
    }

    /**
     * @param cpfMoradorResponsavel eh atribuido um novo morador para receber o visitante.
     */
    public void setCpfMoradorResponsavel(Morador cpfMoradorResponsavel) {
        this.MoradorResponsavel = cpfMoradorResponsavel;
    }
}
