/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classeauxiliar;

import java.util.Date;

/**
 *
 * @author Mattskywalker
 */
public class Conta {
    
    private String codigo;
    private String descricao;
    private Date dataInicial; 
    private Date dataVencimento;
    private double valor;
    private TipoConta tipoConta;

    public Conta(String descricao,Date dataVencimento, double valor, String codigo,TipoConta tipoConta) {
        this.dataInicial = new Date();
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.tipoConta = tipoConta;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataInicial() {
        return dataInicial;
    }
    
    

}

