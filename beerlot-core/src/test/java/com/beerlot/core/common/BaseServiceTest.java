package com.beerlot.core.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class BaseServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    protected <T> T save(T entity) {
        T persist = entityManager.persist(entity);
        entityManager.flush();
        entityManager.clear();
        return persist;
    }

    protected  <T> T merge(T entity) {
        return entityManager.merge(entity);
    }
}
