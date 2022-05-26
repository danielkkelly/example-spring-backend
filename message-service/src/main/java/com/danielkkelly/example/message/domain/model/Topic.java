package com.danielkkelly.example.message.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Topic {
    @Id
    @Column("topic_id")
    private Long id;
    private String topicName;
    private Date createdDate;
    private Date updatedDate;
}

