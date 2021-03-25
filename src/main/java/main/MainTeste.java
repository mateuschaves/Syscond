package main;

import dao.JPAUtil;
import pojos.Carro;
import pojos.Morador;
import dao.CarroDAO;
import dao.CarroDaoInterface;
import dao.MoradorDAO;
import dao.MoradorDaoInterface;
//import pojos.Carro;
//import pojos.Morador;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.*;

public class MainTeste {

    public static void main(String[] args){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        CarroDaoInterface carrosDao = new CarroDAO();
        MoradorDaoInterface moradorDao = new MoradorDAO();

        Set<Carro> setCarros = new HashSet<Carro>();

        Morador morador = new Morador("123.445.789-31","Darth-Vader",setCarros);

        Carro carro = new Carro("SIT-6666","I8","PRETO",morador);
        Carro carro2 = new Carro("XXX-9999","VOYAGE 2020 1.6","ROSA",morador);

        setCarros.add(carro);
        setCarros.add(carro2);


        moradorDao.adicionar(morador);
        carrosDao.adicionar(carro);
        carrosDao.adicionar(carro2);



        //System.out.println("Carro: " + carros.procurar("SEX-0432").getModelo());

        //carros.alterar(new Carro("SEX-0432","VOYAGE 2020 1.6","VERDE",null));


        /*for (Carro a:
             carros.listar()) {
            System.out.println("Cor: " + a.getCor());
        }*/





    }
}
