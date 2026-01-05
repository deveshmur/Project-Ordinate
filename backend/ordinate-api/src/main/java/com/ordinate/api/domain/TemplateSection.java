package com.ordinate.api.domain;

import java.time.Instant;
import java.util.UUID;

public class TemplateSection {

    private final UUID id;
    private final Instant createdAt;

    private final String name;

    private final int orderIndex;

    public TemplateSection(String name, int orderIndex) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be blank");
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.name = name;
        this.orderIndex = orderIndex;
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public int getOrderIndex() {
        return orderIndex;
    }
}
