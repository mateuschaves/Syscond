package main;

import dao.JPAUtil;
import pojos.Carro;
import pojos.Morador;
import repositorios.CarroDAO;
import repositorios.CarroDaoInterface;
import sun.security.util.ArrayUtil;
//import pojos.Carro;
//import pojos.Morador;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.*;

public class MainTeste {

    public static void main(String[] args){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        CarroDaoInterface carros = new CarroDAO();

        Set<Carro> setCarros = new Type()

        Carro carro = new Carro("KKK-1010","GOL 2020 1.6","PRETO",null);
        Carro carro2 = new Carro("SEX-0432","VOYAGE 2020 1.6","ROSA",null);

        setCarros.add(carro);
        setCarros.add(carro2);

        carros.adicionar(carro);
        carros.adicionar(carro2);

        System.out.println("Carro: " + carros.procurar("SEX-0432").getModelo());

        carros.alterar(new Carro("SEX-0432","VOYAGE 2020 1.6","VERDE",null));


        for (Carro a:
             carros.listar()) {
            System.out.println("Cor: " + a.getCor());
        }

        Morador morador = new Morador("69","Mateus Martins",)



    }
}
