/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import java.util.ArrayList;
import pojos.Produto;
import classeauxiliar.Dados;
/**
 *
 * @author Mattskywalker
 */
public class Almoxarifado {

    private ArrayList<Produto> produtos;
    private ArrayList<Dados> historico;

    public Almoxarifado() {
        this.produtos = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public Almoxarifado(ArrayList<Produto> produtos, ArrayList<Dados> historico) {
        this.historico = historico;
        this.produtos = produtos;
    }

    public void deletarProduto(String codigoDeBarras){
        Produto produto = pesquisarProduto(codigoDeBarras);
        if (produto instanceof Produto){
            this.produtos.remove(produto);
            System.out.println("Produto " + codigoDeBarras + " deletado com sucesso");
        }         
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
        
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Dados> getMovimentos() {
        return historico;
    }

    public void setMovimentos(ArrayList<Dados> historico) {
        this.historico = historico;
    }   
}
