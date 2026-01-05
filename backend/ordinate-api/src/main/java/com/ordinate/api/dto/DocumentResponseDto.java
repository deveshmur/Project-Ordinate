package com.ordinate.api.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record DocumentResponseDto(
        UUID id,
        Instant createdAt,
        Instant lastModifiedAt,
        List<DocumentSectionResponseDto> sections,
        List<String> missingSections
) {
    public record DocumentSectionResponseDto(
            UUID id,
            Instant createdAt,
            Instant lastModifiedAt,
            String name,
            int orderIndex,
            String content
    ) {}
}
