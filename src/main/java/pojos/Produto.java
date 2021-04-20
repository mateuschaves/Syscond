/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;
import java.util.ArrayList;

import exceptions.historico.HistoricoVazio;

import javax.persistence.*;

/**
 *   @author Mateus Martins
 *   @author Breno Araujo
 *   @author Eduardo Marinho
 *   @author Mateus Henrique;
 *   descricao:
 *   pojo de produto com seus atributos mapeados, entidades, colouna, chave primaria;
 *   com relacionamento com fornecedor (falta fazer),  (n para 1);
 *   Alem de seus construtores gets e sets;
 *   nome deve ser obrigatorio e unico;
 *   codigo deve ser obrigatorio, unico e deve aceitar apenas numeros;
 *   preco deve ser obrigatorio e deve aceitar apenas numeros;
 *   quantidade deve ser obrigatorio e deve aceitar apenas numeros;
 *   precoMedio deve ser calculado automaticamente.
 */

@Entity
@Table(name = "produto",schema = "syscond")
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
    @Column
    private double precoMedio;

    private ArrayList<Double> historicoPreco = new ArrayList<>();

    public Produto() {
    }

    /**
     * @param nome ,
     * @param preco ,
     * @param codigo e
     * @param quantidade sao os parametros usados nesse segundo construtor de produto, pois o primeiro construtor eh vazio.
     */
    public Produto(String nome, double preco, String codigo, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    /**
     * @param nome ,
     * @param preco e
     * @param quantidade sao os parametros usados nesse terceiro construtor de produto.
     */
    public Produto(String nome, double preco, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    /**
     * @return retorna o nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return retorna o preco do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @return retorna o codigo do produto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return retorna a quantidade do produto.
     */
    public double getQuantidade() {
        return quantidade;
    }

    /*public double getPrecoMedio() throws HistoricoVazio {
        return negocios.Almoxarifado.calculaPrecoMedio(this.historicoPreco);
    }
*/
    /**
     * @return retorna o historico de precos dos produtos.
     */
    public ArrayList<Double> getHistoricoPreco() {
        return historicoPreco;
    }
    
    
    
}
