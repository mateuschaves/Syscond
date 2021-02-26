/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import java.util.ArrayList;
import java.util.Date;
import java.lang.Math;

import pojos.Produto;
import classeauxiliar.Dados;
/**
 *
 * @author Mattskywalker
 */
public class Almoxarifado {

    private ArrayList<Produto> produtos;
    private ArrayList<Dados> historico;
    private final static Double NIVEL_CRITICO = 2.0;

    public Almoxarifado() {
        this.produtos = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public Almoxarifado(ArrayList<Produto> produtos, ArrayList<Dados> historico) {
        this.historico = historico;
        this.produtos = produtos;
    }

    public boolean cadastrarProduto(Produto produto){
        if(produto instanceof Produto){
            if(produto.getQuantidade() > 0){
                Dados dado = new Dados(produto, 1, (int) Math.round(produto.getQuantidade()), new Date(System.currentTimeMillis()));
                this.produtos.add(produto);
                this.historico.add(dado);
                return true;
            }
        }
        return false;
    }

    public Double calculaPrecoMedio(ArrayList<Double> historicoPreco){
        Double total = 0.0;
        for (Double preco : historicoPreco) {
            total += preco;
        }
        Double media = total/historicoPreco.size();
        return media;
    }
    
    public boolean deletarProduto(String codigoDeBarras){
        Produto produto = pesquisarProduto(codigoDeBarras);
        if (produto instanceof Produto){
            this.produtos.remove(produto);
            System.out.println("Produto " + codigoDeBarras + " deletado com sucesso");
            return true;
        }         
        return false;
    }

    public ArrayList<Produto> estoqueCritico(){
        ArrayList<Produto> produtosEscassos = new ArrayList<Produto>();
        for (Produto produto : this.produtos) {
            if(produto.getQuantidade() == NIVEL_CRITICO){
                produtosEscassos.add(produto);
            }
        }
        return produtosEscassos;
    }

    public Produto pesquisarProduto(String codigoDeBarras){
        for (Produto produto : this.produtos) {
            if(produto.getCodigo().equals(codigoDeBarras)){
                return produto;
            }
        }
        System.out.println("Produto " + codigoDeBarras + " não foi encontrado");
        return null;
    }

    public ArrayList<Dados> relatorioDataMovel(Date inicio, Date fim){
        ArrayList<Dados> historicoDataMovel = new ArrayList<Dados>();
        for (Dados dados : this.historico) {
            Date data = dados.getData();
            if(data.compareTo(fim) < 0){
                if(data.compareTo(inicio) >= 0){
                    historicoDataMovel.add(dados);
                }
            }else{
                return historicoDataMovel;
            }
        }
        return null;
    }

    public boolean saidaProduto(String codigoDeBarras){
        Produto produto = pesquisarProduto(codigoDeBarras);
        if(produto instanceof Produto){
            if(produto.getQuantidade() > 0){
                Dados dado = new Dados(produto, 0, (int) (Math.round(produto.getQuantidade()) - 1), new Date(System.currentTimeMillis()));
                this.historico.add(dado);
                return true;
            }
        }
        return false;
    }

    
}
