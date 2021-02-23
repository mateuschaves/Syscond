/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.util.ArrayList;

/**
 *
 * @author Mattskywalker
 */
public class Almoxarifado {

    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Movimento> movimentos = new ArrayList<>();

    public Almoxarifado(ArrayList<Produto> produtos, ArrayList<Movimento> movimentos) {
        this.movimentos = movimentos;
        this.produtos = produtos;
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(ArrayList<Movimento> movimentos) {
        this.movimentos = movimentos;
    }   
}
