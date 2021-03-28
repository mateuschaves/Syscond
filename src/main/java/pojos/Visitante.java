/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.*;

/**
 *
 * @author Mattskywalker
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

    public Visitante(String cpf, String nome, Morador MoradorResponsavel) {
        this.cpf = cpf;
        this.nome = nome;
        this.MoradorResponsavel = MoradorResponsavel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JoinColumn(name = "moradorResponsavel")
    public Morador getCpfMoradorResponsavel() {
        return MoradorResponsavel;
    }

    public void setCpfMoradorResponsavel(Morador cpfMoradorResponsavel) {
        this.MoradorResponsavel = cpfMoradorResponsavel;
    }
}
