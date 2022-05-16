package com.danielkkelly.example.domain.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Topic implements Stamped {
    private Long id;
    private String topicName;
    private Date createdDate;
    private Date updatedDate;
}
