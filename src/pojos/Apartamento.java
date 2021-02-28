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
public class Apartamento {

    private int numero;
    private int andar;
    private String bloco;
    private ArrayList<Morador> morador = new ArrayList<>();

    public Apartamento(int numero, int andar, String bloco) {
        this.numero = numero;
        this.andar = andar;
        this.bloco = bloco;

    }

    public ArrayList<Morador> getMorador() {
        return morador;
    }

    public void setMorador(ArrayList<Morador> morador) {
        this.morador = morador;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

}
