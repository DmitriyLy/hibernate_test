package dmly.hibernate.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestUtils {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate_Test");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void destroy() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
