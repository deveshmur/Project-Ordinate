package com.ordinate.api.domain;

import java.time.Instant;
import java.util.UUID;

public class DocumentSection {

    private final UUID id;
    private final Instant createdAt;
    private Instant lastModifiedAt;

    private final String name;
    private final int orderIndex;

    private String content;

    public DocumentSection(String name, int orderIndex) {
        this(name, orderIndex, "");
    }

    public DocumentSection(String name, int orderIndex, String content) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.lastModifiedAt = this.createdAt;
        this.name = name;
        this.orderIndex = orderIndex;
        this.content = content == null ? "" : content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
        touch();
    }

    public void touch() {
        this.lastModifiedAt = Instant.now();
    }
}
