package dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    /**
     *
     * @return retorna um EntityManager, toda essa classe com seus metodos sao muito importantes;
     * Alem disso essa classe se comunica com o persistence.xml para persistir os dados em um banco.
     */
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void close(){
        emf.close();
    }

}
