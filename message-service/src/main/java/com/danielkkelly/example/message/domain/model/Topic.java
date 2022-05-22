package com.danielkkelly.example.message.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Topic {
    private Long id;
    private String topicName;
    private Date createdDate;
    private Date updatedDate;
}

