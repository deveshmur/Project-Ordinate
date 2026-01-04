package com.ordinate.api.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record TemplateResponseDto(
        UUID id,
        Instant createdAt,
        Instant lastModifiedAt,
        List<TemplateSectionResponseDto> sections
) {
    public record TemplateSectionResponseDto(
            UUID id,
            Instant createdAt,
            int orderIndex
    ) {}
}
