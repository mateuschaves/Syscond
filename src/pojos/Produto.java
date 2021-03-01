/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;
import java.util.ArrayList;

import exceptions.HistoricoVazio;

/**
 *
 * @author Mattskywalker
 */
public class Produto {
    private String nome;
    private double preco;
    private String codigo;
    private double quantidade;
    private double precoMedio;

    private ArrayList<Double> historicoPreco = new ArrayList<>();

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

    public double getPrecoMedio() throws HistoricoVazio {
        return negocios.Almoxarifado.calculaPrecoMedio(this.historicoPreco);
    }

    public ArrayList<Double> getHistoricoPreco() {
        return historicoPreco;
    }
    
    
    
}
