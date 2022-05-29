package com.danielkkelly.example.message.service;

import com.danielkkelly.example.message.domain.model.Topic;
import com.danielkkelly.example.message.repository.TopicRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Service
public class TopicService extends BaseService<Topic> {
    @Inject
    private TopicRepository repository;

    @Override
    protected R2dbcRepository<Topic, Long> getRepository() {
        return this.repository;
    }

    public Flux<Topic> findAll() {
       return repository.findAll();
    }

    public Mono<Topic> findById(final Long id) {
        return repository.findById(id);
    }

    public Mono<Topic> edit(final Long id, final Topic topic) {
        return repository.findById(id)
                .flatMap(updated -> {
                    updated.setTopicName(topic.getTopicName());
                    return repository.save(updated);
                });
    }
}
