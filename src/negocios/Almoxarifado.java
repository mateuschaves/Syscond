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
import exceptions.HistoricoVazio;
import exceptions.ProdutoEmEstoqueCritico;
import exceptions.ProdutoJaExistente;
import exceptions.ProdutoNaoEncontrado;
import interfacedecodigo.AlmoxarifacoInterface;

/**
 *
 * @author Mattskywalker
 */
public class Almoxarifado implements AlmoxarifacoInterface {

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

    public void adicionar(Produto produto, int... posicao) throws ProdutoJaExistente {
        try {
            procurar(produto.getCodigo());
            throw new ProdutoJaExistente(produto.getCodigo());
        } catch (ProdutoNaoEncontrado e) {
            Dados dado = new Dados(produto, 1, (int) Math.round(produto.getQuantidade()),
                    new Date(System.currentTimeMillis()));
            this.historico.add(dado);
            if (posicao.length > 0)
                this.produtos.add(posicao[0], produto);
            else
                this.produtos.add(produto);
        }
    }

    public static Double calculaPrecoMedio(ArrayList<Double> historicoPreco) throws HistoricoVazio {
        if (historicoPreco.size() == 0)
            throw new HistoricoVazio();
        Double total = 0.0;
        for (Double preco : historicoPreco) {
            total += preco;
        }
        return total / historicoPreco.size();
    }

    @Override
    public ArrayList<Produto> listar() throws ProdutoNaoEncontrado {
        if (this.produtos.size() == 0) {
            throw new ProdutoNaoEncontrado("");
        }
        return this.produtos;
    }

    @Override
    public void alterar(String codigoDeBarras, Produto novo) throws ProdutoNaoEncontrado, ProdutoJaExistente {
        int posicao = procurarPosicao(codigoDeBarras);
        this.produtos.remove(this.produtos.get(posicao));
        adicionar(novo, posicao);
    }

    public ArrayList<Produto> estoqueCritico() {
        ArrayList<Produto> produtosEscassos = new ArrayList<Produto>();
        for (Produto produto : this.produtos) {
            if (produto.getQuantidade() == NIVEL_CRITICO) {
                produtosEscassos.add(produto);
            }
        }
        return produtosEscassos;
    }

    public Produto procurar(String codigoDeBarras) throws ProdutoNaoEncontrado {
        for (Produto produto : this.produtos) {
            if (produto.getCodigo().equals(codigoDeBarras)) {
                return produto;
            }
        }
        throw new ProdutoNaoEncontrado(codigoDeBarras);
    }

    private int procurarPosicao(String codigoDeBarras) throws ProdutoNaoEncontrado {
        for (int i = 0; i < this.produtos.size(); i++) {
            if (this.produtos.get(i).getCodigo().equals(codigoDeBarras)) {
                return i;
            }
        }
        throw new ProdutoNaoEncontrado(codigoDeBarras);
    }

    public ArrayList<Dados> relatorioDataMovel(Date inicio, Date fim) throws HistoricoVazio {
        ArrayList<Dados> historicoDataMovel = new ArrayList<Dados>();
        for (Dados dados : this.historico) {
            Date data = dados.getData();
            if ((data.compareTo(fim) < 0)) {
                if (data.compareTo(inicio) >= 0) {
                    historicoDataMovel.add(dados);
                }
            } else {
                return historicoDataMovel;
            }
        }
        throw new HistoricoVazio();
    }

    public void saidaProduto(String codigoDeBarras) throws ProdutoNaoEncontrado, ProdutoEmEstoqueCritico {
        Produto produto = procurar(codigoDeBarras);
        if (produto.getQuantidade() > 0) {
            Dados dado = new Dados(produto, 0, (int) (Math.round(produto.getQuantidade()) - 1),
                    new Date(System.currentTimeMillis()));
            this.historico.add(dado);
        }else{
            throw new ProdutoEmEstoqueCritico(codigoDeBarras);
        }
    }

}
