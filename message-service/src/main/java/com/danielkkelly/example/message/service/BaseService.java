package com.danielkkelly.example.message.service;

import com.danielkkelly.example.commons.domain.model.R2dbcStamped;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
public abstract class BaseService<T extends Object> {
    protected abstract R2dbcRepository<T, Long> getRepository();

    public Mono<T> create(final T entity) {
        log.trace(this.getClass().getName() + " create() called");
        if (entity instanceof R2dbcStamped) {
            ((R2dbcStamped) entity).setCreatedDate(LocalDateTime.now());
            ((R2dbcStamped) entity).setUpdatedDate(LocalDateTime.now());
        }
        return getRepository().save(entity);
    }
}