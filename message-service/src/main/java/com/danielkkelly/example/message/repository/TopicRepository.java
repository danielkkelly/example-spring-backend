package com.danielkkelly.example.message.repository;

import com.danielkkelly.example.message.domain.model.Topic;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TopicRepository extends R2dbcRepository<Topic, Long> {
}
