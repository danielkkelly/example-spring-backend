package com.danielkkelly.example.commons.domain.model;


import java.time.LocalDateTime;

public interface R2dbcStamped {
    void setCreatedDate(LocalDateTime date);

    LocalDateTime getCreatedDate();

    void setUpdatedDate(LocalDateTime date);

    LocalDateTime getUpdatedDate();
}
