package com.epul.projetoeuvres.persistence;

import javax.persistence.*;

public abstract class EntityService {
    protected EntityManager entityManager;
    protected EntityManagerFactory entityManagerFactory;

    protected EntityTransaction startTransaction() {
        entityManagerFactory = Persistence.createEntityManagerFactory("oeuvresjpa");
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager.getTransaction();
    }
}
