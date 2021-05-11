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
 *
 *   descricao:
 *   pojo de produto com seus atributos mapeados, entidades, colouna, chave primaria;
 *   Alem de seus construtores gets e sets;
 *   nome deve ser obrigatorio e unico;
 *   codigo deve ser obrigatorio, unico e deve aceitar apenas numeros;
 *   preco deve ser obrigatorio e deve aceitar apenas numeros;
 *   quantidade deve ser obrigatorio e deve aceitar apenas numeros;
 *   precoMedio deve ser calculado automaticamente.
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
        this.codigo = "789" + Math.abs(new Random().nextInt((int) Math.pow(10, 10)));
        this.nome = "";
        this.preco = 0.0;
        this.quantidade = 0.0;
    }

    /**
     * @param nome ,
     * @param preco e
     * @param quantidade sao os parametros usados nesse segundo construtor de produto.
     */
    public Produto(String nome, double preco, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    /**
     * @param nome ,
     * @param preco ,
     * @param codigo e
     * @param quantidade sao os parametros usados nesse terceiro construtor de produto.
     */
    public Produto(String nome, double preco, String codigo, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    /**
     * @return retorna o nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome eh atribuido um novo nome para o produto.
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * @return retorna o preco do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     *
     * @param preco eh atribuido um novo preco para o produto.
     */
    public void setPreco(double preco){
        this.preco = preco;
    }


    /**
     * @return retorna o codigo do produto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo eh atribuido um novo codigo para o produto.
     */
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    /**
     * @return retorna a quantidade do produto.
     */
    public double getQuantidade() {
        return quantidade;
    }

    /**
     *
     * @param quantidade eh atribuido uma nova quantidade para o produto.
     */
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
