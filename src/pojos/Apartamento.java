/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author Mattskywalker
 */
public class Apartamento {
    
    private int numero;
    private String andar;
    private String bloco;
    private Morador morador;

    public Apartamento(int numero, String andar, String bloco, Morador morador) {
        this.numero = numero;
        this.andar = andar;
        this.bloco = bloco;
        this.morador  = morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public Morador getMorador() {
        return morador;
    }

    
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }
    
    
    
    
}
