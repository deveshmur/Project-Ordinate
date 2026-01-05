package com.ordinate.api.dto;

import com.ordinate.api.domain.TemplateKey;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record TemplateResponseDto(
        UUID id,
        TemplateKey key,
        Instant createdAt,
        Instant lastModifiedAt,
        List<TemplateSectionResponseDto> sections
) {
    public record TemplateSectionResponseDto(
            UUID id,
            Instant createdAt,
            String name,
            int orderIndex
    ) {}
}
