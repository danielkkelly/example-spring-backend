package com.danielkkelly.example.message.repository;

import com.danielkkelly.example.message.domain.model.Topic;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TopicRepository extends ReactiveCrudRepository<Topic, Long> {
}
