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
 * @author Mateus Martins
 * @author Breno Araujo
 * @author Eduardo Marinho
 * @author Mateus Henrique
 *
 * pojo de produto com seus atributos mapeados, entidades, colouna, chave primaria;
 * com relacionamento com fornecedor (falta fazer),  (n para 1);
 * Alem de seus construtores gets e sets.
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

    public Produto(String nome, double preco, String codigo, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    public Produto(String nome, double preco, double quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    /*public double getPrecoMedio() throws HistoricoVazio {
        return negocios.Almoxarifado.calculaPrecoMedio(this.historicoPreco);
    }
*/
    public ArrayList<Double> getHistoricoPreco() {
        return historicoPreco;
    }
    
    
    
}
