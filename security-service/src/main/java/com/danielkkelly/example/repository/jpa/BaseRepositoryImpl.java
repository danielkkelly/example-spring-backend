package com.danielkkelly.example.repository.jpa;

import javax.persistence.EntityManager;

import com.danielkkelly.example.domain.model.AbstractEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public abstract class BaseRepositoryImpl<T extends AbstractEntity<Long>> extends SimpleJpaRepository<T, Long>
    implements BaseRepository<T, Long> {

    private final EntityManager em;
    protected final JPAQueryFactory queryFactory;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void clear() {
        em.clear();
    }
    
    public void detach(T entity) {
        em.detach(entity);
    }
}