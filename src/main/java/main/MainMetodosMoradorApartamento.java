package main;

import dao.*;
import pojos.Apartamento;
import pojos.Carro;
import pojos.Morador;
import pojos.Visitante;

import java.util.ArrayList;
import java.util.List;

public class MainMetodosMoradorApartamento {

    public static void main(String[] args){

        ApartamentoDaoInterface apartamentoDAO = new ApartamentoDAO();
        MoradorDaoInterface moradorDAO = new MoradorDAO();
        CarroDaoInterface carroDAO = new CarroDAO();
        VisitanteDaoInterface visitanteDAO = new VisitanteDAO();
        //dao instanciadas;

        Apartamento ap1 = new Apartamento("1","Primeiro","B");
        Apartamento ap2 = new Apartamento("2","Primeiro","B");
        Apartamento ap3 = new Apartamento("3","Segundo","B");
        Apartamento ap4 = new Apartamento("4","Segundo","B");


        try{
            apartamentoDAO.adicionar(ap1);
            apartamentoDAO.adicionar(ap2);
            apartamentoDAO.adicionar(ap3);
            apartamentoDAO.adicionar(ap4);

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }//apartamentos já vão estar cadastrados;




        List<Carro> listaCarros = new ArrayList<>();


        Morador m1 = new Morador("6","Hamazura",ap1,listaCarros);
        //Carro c1 = new Carro("H-123","Hilux","Laranja",m1);

        //listaCarros.add(c1);


        moradorDAO.adicionar(m1);
        //carroDAO.adicionar(c1);


        //testando alterar;

        Carro carroNovo = new Carro("M4-A1","Honda civic","Rosa",m1);

        carroDAO.adicionar(carroNovo);

        //criando um visitante;

        Visitante v1 = new Visitante("1999","Kaifuku",m1);



        Visitante v2 = new Visitante("4","Touma",moradorDAO.procurar("5"));

        visitanteDAO.adicionar(v1);
        visitanteDAO.adicionar(v2);





    }

}
