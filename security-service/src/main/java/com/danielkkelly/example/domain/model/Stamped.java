package com.danielkkelly.example.domain.model;

import java.util.Date;

import lombok.Getter;

/**
 * Created by dkelly on 11/19/16.
 */
public interface Stamped {
    void setCreatedDate(final Date createdDate);
    Date getCreatedDate();

    void setUpdatedDate(final Date updatedDate);
    Date getUpdatedDate();
}