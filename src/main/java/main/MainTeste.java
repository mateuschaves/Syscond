package main;


import dao.*;
import exceptions.carro.CarroJaExistente;
import exceptions.morador.MoradorJaExistente;
import exceptions.produto.ProdutoNaoEncontrado;
import pojos.Carro;
import pojos.Morador;
import pojos.Produto;
//import pojos.Carro;
//import pojos.Morador;

import java.util.*;

public class MainTeste {

    public static void main(String[] args){


        CarroDaoInterface carrosDao = new CarroDAO();
        MoradorDaoInterface moradorDao = new MoradorDAO();

        List<Carro> listaCarros = new ArrayList<>();

        Morador morador = new Morador("123.445.789-31","Darth-Vader",listaCarros);

        Carro carro = new Carro("SIT-6666","I8","PRETO",morador);
        Carro carro2 = new Carro("XXX-9999","VOYAGE 2020 1.6","ROSA",morador);

        listaCarros.add(carro);
        listaCarros.add(carro2);

        try {
            moradorDao.adicionar(morador);
        } catch (MoradorJaExistente e) {
            System.out.println("O morador de cpf " + e.getMessage() + " já foi adicionado no sistema.");
        }
        try {
            carrosDao.adicionar(carro);
        } catch (CarroJaExistente e) {
            System.out.println("O carro de placa " + e.getMessage() + " já foi adicionado no sistema.");
        }
        try {
            carrosDao.adicionar(carro2);
        } catch (CarroJaExistente e) {
            System.out.println("O carro de placa " + e.getMessage() + " já foi adicionado no sistema.");
        }

        ProdutoDaoInterface produtos = new ProdutoDAO();
        Produto produto = new Produto("Sabão em pó",10,"1",12);

        //produtos.adicionar(produto);
        List<Produto> produtoList = new ArrayList<>();

        try {
            produtos.alterar(produto);
        } catch (ProdutoNaoEncontrado e) {
            System.out.println("O produto com código de barras " + e.getMessage() + " não foi encontrado no sistema.");
        }
        try{

            produtoList = produtos.listar();

            for (Produto a:
            produtoList) {

                System.out.println("Produto: " + a.getNome());
            }
        }catch (Exception e){

            System.err.println(e.getMessage());

        }

        try {
            produtos.remover(produto);
        } catch (ProdutoNaoEncontrado e) {
            System.out.println("O produto com código de barras " + e.getMessage() + " não foi encontrado no sistema.");
        }

        try{

            produtoList = produtos.listar();

            for (Produto a:
                    produtoList) {

                System.out.println("Produto: " + a.getNome());
            }
        }catch (Exception e){

            System.err.println(e.getMessage());

        }
        JPAUtil.close();

    }
}
