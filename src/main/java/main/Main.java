package main;

import dao.JPAUtil;

import javax.persistence.Persistence;
import pojos.Fornecedor;
public class Main {

    public static void main(String[] args){

        JPAUtil emf;
        Fornecedor fornecedor = new Fornecedor("123","Mateus","81 993146584");

        emf.getEntityManager().getTransaction().begin();
        emf.getEntityManager().persist(fornecedor);
        emf.getEntityManager().getTransaction().commit();
        emf.getEntityManager().close();

    }
}
