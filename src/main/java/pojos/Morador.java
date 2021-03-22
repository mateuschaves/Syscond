/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;


import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private String nome;
    //@OneToMany(mappedBy = "proprietario")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario")
    private Set<Carro> carros;

    public Morador() {
    }

    public Morador(String cpf, String nome, Set<Carro> carros) {
        this.cpf = cpf;
        this.nome = nome;
        this.carros = carros;
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

    public Set<Carro> getCarros() {
        return carros;
    }

    public void setCarros(Set<Carro> carros) {
        this.carros = carros;
    }
}