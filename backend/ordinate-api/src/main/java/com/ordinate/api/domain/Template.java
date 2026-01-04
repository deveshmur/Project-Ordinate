package com.ordinate.api.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Template {
    private final UUID id;
    private final TemplateKey key;

    private final Instant createdAt;
    private Instant lastModifiedAt;

    private final List<TemplateSection> sections = new ArrayList<>();

    public Template(TemplateKey key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        this.id = UUID.randomUUID();
        this.key = key;
        this.createdAt = Instant.now();
        this.lastModifiedAt = this.createdAt;
    }

    public UUID getId() {
        return id;
    }

    public TemplateKey getKey() {
        return key;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public List<TemplateSection> getSections() {
        return Collections.unmodifiableList(sections);
    }

    public void addSection(TemplateSection section) {
        if (section == null) throw new IllegalArgumentException("section cannot be null");
        this.sections.add(section);
        touch();
    }

    private void touch() {
        this.lastModifiedAt = Instant.now();
    }
}