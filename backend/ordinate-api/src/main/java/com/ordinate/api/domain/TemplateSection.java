package com.ordinate.api.domain;

import java.time.Instant;
import java.util.UUID;

public class TemplateSection {
    private final UUID id;
    private final Instant createdAt;

    private final int orderIndex;

    public TemplateSection(int orderIndex) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.orderIndex = orderIndex;
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public int getOrderIndex() {
        return orderIndex;
    }
}
