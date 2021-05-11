package main;

import dao.*;
import negocios.VisitanteNegocios;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;
import pojos.Visitante;

import java.util.ArrayList;
import java.util.List;

/**
 * descricao: essa main procura testar as classes Morador e Apartamento com suas
 * funcionalidades e claro no nosso pc rodava professor! kkkk :)
 */

public class MainMetodosMoradorApartamento {

    public static void main(String[] args) {

        ApartamentoDaoInterface apartamentoDAO = new ApartamentoDAO();
        MoradorDaoInterface moradorDAO = new MoradorDAO();
        CarroDaoInterface carroDAO = new CarroDAO();
        VisitanteDaoInterface visitanteDAO = new VisitanteDAO();
        //dao instanciadas;

        Apartamento ap1 = new Apartamento("1", "Primeiro", "B");
        Apartamento ap2 = new Apartamento("2", "Primeiro", "B");
        Apartamento ap3 = new Apartamento("3", "Segundo", "B");
        Apartamento ap4 = new Apartamento("4", "Segundo", "B");


        try {
            apartamentoDAO.adicionar(ap1);
            apartamentoDAO.adicionar(ap2);
            apartamentoDAO.adicionar(ap3);
            apartamentoDAO.adicionar(ap4);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }//apartamentos já vão estar cadastrados;

        List<Carro> listaCarros = new ArrayList<>();


        Morador m1 = new Morador(null, "Hamazura", ap1, listaCarros);

        moradorDAO.adicionar(m1);
        VisitanteNegocios visitanteNegocios = new VisitanteNegocios();

        System.out.println(" ------------------ " + visitanteNegocios.pesquisar(new
                Visitante("123.627.134.-31",null,null)).getNome());

    }
}
