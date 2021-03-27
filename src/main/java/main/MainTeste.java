package main;


import dao.*;
import pojos.Carro;
import pojos.Morador;
import pojos.Produto;
//import pojos.Carro;
//import pojos.Morador;

import java.util.*;

public class MainTeste {

    public static void main(String[] args){

        /*
        CarroDaoInterface carrosDao = new CarroDAO();
        MoradorDaoInterface moradorDao = new MoradorDAO();

        Set<Carro> setCarros = new HashSet<>();

        Morador morador = new Morador("123.445.789-31","Darth-Vader",setCarros);

        Carro carro = new Carro("SIT-6666","I8","PRETO",morador);
        Carro carro2 = new Carro("XXX-9999","VOYAGE 2020 1.6","ROSA",morador);

        setCarros.add(carro);
        setCarros.add(carro2);

        moradorDao.adicionar(morador);
        carrosDao.adicionar(carro);
        carrosDao.adicionar(carro2);

        */


        ProdutoDaoInterface produtos = new ProdutoDAO();
        Produto produto = new Produto("Sabão em pó",10,"1",12);

        //produtos.adicionar(produto);
        List<Produto> produtoList = new ArrayList<>();

        produtos.alterar(produto);
        try{

            produtoList = produtos.listar();

            for (Produto a:
            produtoList) {

                System.out.println("Produto: " + a.getNome());
            }
        }catch (Exception e){

            System.err.println(e.getMessage());

        }

        produtos.remover(produto);

        try{

            produtoList = produtos.listar();

            for (Produto a:
                    produtoList) {

                System.out.println("Produto: " + a.getNome());
            }
        }catch (Exception e){

            System.err.println(e.getMessage());

        }



    }
}
