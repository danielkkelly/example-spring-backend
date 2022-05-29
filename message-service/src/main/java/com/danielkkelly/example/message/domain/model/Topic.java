package com.danielkkelly.example.message.domain.model;

import com.danielkkelly.example.commons.domain.model.R2dbcStamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Topic implements R2dbcStamped {
    @Id
    @Column("topic_id")
    private Long id;
    @NotNull
    @Size(max = 100)
    private String topicName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}

