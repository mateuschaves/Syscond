/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.*;
/**
 *
 *   @author Mateus Martins
 *   @author Breno Araujo
 *   @author Eduardo Marinho
 *   @author Mateus Henrique
 *
 *   pojo de carro com seus atributos mapeados, entidades, colouna, chave primaria e
 *   com relacionamento com morador, varios carros para um morador (n para 1).
 *   Alem de seus construtores gets e sets.
 */


@Entity
@Table(name = "carro",schema = "syscond")
public class Carro {
    @Id
    private String placa;
    @Column
    private String modelo;
    @Column
    private String cor;
    @ManyToOne(fetch = FetchType.LAZY)
    private Morador proprietario;

    public Carro() {
    }

    public Carro(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public Carro(String placa, String modelo, String cor, Morador proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.proprietario = proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }


    @JoinColumn(name = "proprietarioCpf")
    public Morador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Morador proprietario) {
        this.proprietario = proprietario;
    }
}
