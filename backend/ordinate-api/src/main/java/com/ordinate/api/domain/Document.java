package com.ordinate.api.domain;

import java.time.Instant;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Document {

private final UUID id;
    private final Instant createdAt;
    private Instant lastModifiedAt;

    private final List<DocumentSection> sections = new ArrayList<>();

    public Document() {
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

    public List<DocumentSection> getSections() {
        return Collections.unmodifiableList(sections);
    }

    public void addSection(DocumentSection section) {
        if (section == null) throw new IllegalArgumentException("section cannot be null");
        this.sections.add(section);
        touch();
    }

    public void touch() {
        this.lastModifiedAt = Instant.now();
    }
}
