package com.danielkkelly.example.service;

import java.util.Date;
import java.util.List;

import com.danielkkelly.example.domain.exception.NotFoundException;
import com.danielkkelly.example.domain.model.AbstractEntity;
import com.danielkkelly.example.domain.model.Stamped;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseService <T extends AbstractEntity<Long>> {
    /**
     * Implementations must supply a DAO for their Entity Type.
     * @return a JpaRepository implementation.
     */
    protected abstract JpaRepository<T, Long> getRepository();

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public T findById(final Long id) {
        T entity = getRepository().findById(id)
                    .orElseThrow(() -> new NotFoundException(id));
        return entity;
    }

    public T create(final T entity) {
        log.trace(this.getClass().getName() + " create() called");
        if (entity instanceof Stamped) {
            ((Stamped) entity).setCreatedDate(new Date());
            ((Stamped) entity).setUpdatedDate(new Date());
        }
        return getRepository().save(entity);
    }

    public T edit(final T entity) {
        if (entity instanceof Stamped) {
            ((Stamped) entity).setUpdatedDate(new Date());
        }
        return getRepository().save(entity);
    }

    public void delete(final Long id) {
        getRepository().deleteById(id);
    } 
}