package com.danielkkelly.example.message.controller;

import com.danielkkelly.example.message.domain.model.Topic;
import com.danielkkelly.example.message.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Inject
    private TopicService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Topic> create(@RequestBody @Valid Topic topic) {
        return service.create(topic);
    }

    @GetMapping
    public Flux<Topic> findAllTopics() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Topic>> edit(@PathVariable Long id, @RequestBody Topic topic) {
        return service.edit(id, topic)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Topic>> findById(@PathVariable Long id) {
        Mono<Topic> topic = service.findById(id);
        return topic.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
