package com.beerlot.core.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

@DataJpaTest(includeFilters = {@ComponentScan.Filter(Repository.class)})
public class BaseRepositoryTest {

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

