package main;

import dao.*;
import exceptions.carro.CarroJaExistente;
import exceptions.morador.MoradorJaExistente;
import exceptions.morador.MoradorNaoEncontrado;
import exceptions.visitante.VisitanteJaExistente;
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


        try {
            moradorDAO.adicionar(m1);
        } catch (MoradorJaExistente e) {
            System.out.println("O morador de cpf " + e.getMessage() + " já foi adicionado no sistema.");
        }
        //carroDAO.adicionar(c1);


        //testando alterar;

        Carro carroNovo = new Carro("M4-A1","Honda civic","Rosa",m1);

        try {
            carroDAO.adicionar(carroNovo);
        } catch (CarroJaExistente e) {
            System.out.println("O carro de placa " + e.getMessage() + " já foi adicionado no sistema.");
        }

        //criando um visitante;

        Visitante v1 = new Visitante("1999","Kaifuku",m1);


        Visitante v2 = null;
        try {
            v2 = new Visitante("4","Touma",moradorDAO.procurar("5"));
        } catch (MoradorNaoEncontrado e) {
            System.out.println("O morador com cpf " + e.getMessage() + " não foi encontrado no sistema.");
        }

        try {
            visitanteDAO.adicionar(v1);
        } catch (VisitanteJaExistente e) {
            System.out.println("O visitante de cpf " + e.getMessage() + " já foi adicionado no sistema.");
        }
        try {
            visitanteDAO.adicionar(v2);
        } catch (VisitanteJaExistente e) {
            System.out.println("O visitante de cpf " + e.getMessage() + " já foi adicionado no sistema.");
        }


        //try {
             //visitanteDAO.alterar(new Visitante("10", "Hamazura", moradorDAO.procurar("5")));
            //apartamentoDAO.alterar(new Apartamento("4", "HELL", "5º"));
            //carroDAO.alterar(new Carro("M4-A1","Honda civic","Vermelho Sangue!",m1));
            // moradorDAO.alterar(new Morador("6","Kaifuku",ap1,listaCarros));

        //}catch (Exception e){
        //    System.err.println("fudeu");
        //}
    }

}
