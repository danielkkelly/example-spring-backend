package com.danielkkelly.example.domain.model;

import java.io.Serializable;

public abstract class AbstractEntity<KeyType extends Serializable> {
    /**
     * Implementations must supply a way to get the Entity's identifier.
     * @return the Entity's identifier.
     */
    abstract KeyType getId();
}