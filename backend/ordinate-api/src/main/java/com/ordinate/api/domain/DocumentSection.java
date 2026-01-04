package com.ordinate.api.domain;

import java.time.Instant;
import java.util.UUID;

public class DocumentSection {
    private final UUID id;
    private final Instant createdAt;
    private Instant lastModifiedAt;

    public DocumentSection() {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.lastModifiedAt = this.createdAt;
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

    public void touch() {
        this.lastModifiedAt = Instant.now();
    }
}
