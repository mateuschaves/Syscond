package main;

import dao.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import pojos.Fornecedor;
public class Main {

    public static void main(String[] args){

        EntityManager em = JPAUtil.getEntityManager();
        Fornecedor fornecedor = new Fornecedor("123","Mateus","81 993146584");

        em.getTransaction().begin();
        em.persist(fornecedor);
        em.getTransaction().commit();
        em.close();

    }
}
