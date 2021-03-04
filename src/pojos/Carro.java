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
public class Carro {
    
    private String modelo;
    private String placa;
    private String cor;
    private Morador proprietario;
    
    
    
    Carro(Morador proprietario, String modelo, String placa, String cor){
    	
    	this.proprietario = proprietario;
    	this.modelo = modelo;
    	this.placa = placa;
    	this.cor = cor;
    }

	public String getModelo() {
      
		return this.modelo;
		
	}
	
	public void setModelo(String modelo){
		
		this.modelo = modelo;
		
	}
	
	public String getplaca() {
	      
		return this.placa;
		
	}
	
	public void setplaca(String placa){
		
		this.placa = placa;
		
	}
	
	
	public String getCor() {
	      
		return this.cor;
		
	}
	
	public void setCor(String cor){
		
		this.cor = cor;
		
	}
	
	
	public Morador getProprietario() {
	      
		return this.proprietario;
		
	}
	
	public void setProprietario(Morador proprietario){
		
		this.proprietario = proprietario;	
	}	
}	