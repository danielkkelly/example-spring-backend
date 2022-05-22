package com.danielkkelly.example.message.controller;

import com.danielkkelly.example.message.domain.model.Topic;
import com.danielkkelly.example.message.service.TopicService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@RestController
public class TopicController {

    @Inject
    private TopicService service;
    @GetMapping("/topics")
    public Flux findAllTopics() {
        return service.findAll();
    }

    @GetMapping("/topics/{id}")
    public Mono findById(@PathVariable Long id) {
        return service.findById(id);
    }
    @PostMapping("/topics")
    public Mono create(@RequestBody Topic topic) {
        return service.save(topic);
    }
}
