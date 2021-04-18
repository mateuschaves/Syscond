/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Random;

/**
 * @author Mattskywalker
 */

@Entity
@Table(name = "produto", schema = "syscond")
public class Produto {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String codigo;
    @Column
    private String nome;
    @Column
    private double preco;
    @Column
    private double quantidade;
    /*@Column
    private double precoMedio; FIXME Isso precisa ser implementado ou removido
    /*private ArrayList<Double> historicoPreco; FIXME Isso precisa ser implementado ou removido*/

    public Produto() {
        this.codigo = "789" + new Random().nextInt((int) Math.pow(10, 10));
        this.nome = "";
        this.preco = 0.0;
        this.quantidade = 0.0;
    }

    public Produto(String nome, double preco, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto(String nome, double preco, String codigo, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco){
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade){
        this.quantidade = quantidade;
    }



    /*
    public double getPrecoMedio() throws HistoricoVazio {
        return negocios.Almoxarifado.calculaPrecoMedio(this.historicoPreco);
    } FIXME Isso precisa ser implementado ou removido


    public ArrayList<Double> getHistoricoPreco() {
        return historicoPreco;
    } FIXME Isso precisa ser implementado ou removido
    */


}
