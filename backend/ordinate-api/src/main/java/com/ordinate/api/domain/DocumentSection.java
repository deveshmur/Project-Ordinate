package com.ordinate.api.domain;

import java.time.Instant;
import java.util.UUID;

public class DocumentSection {

    private final UUID id;
    private final Instant createdAt;
    private Instant lastModifiedAt;

    private final String name;
    private final int orderIndex;

    public DocumentSection(String name, int orderIndex) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.lastModifiedAt = this.createdAt;
        this.name = name;
        this.orderIndex = orderIndex;
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public String getName() {
        return name;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void touch() {
        this.lastModifiedAt = Instant.now();
    }
}
