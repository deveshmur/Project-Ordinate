package com.ordinate.api.service;

import com.ordinate.api.domain.Document;
import com.ordinate.api.domain.DocumentSection;
import com.ordinate.api.dto.DocumentResponseDto;
import com.ordinate.api.dto.ProcessTextRequestDto;
import org.springframework.stereotype.Service;

@Service
public class TextProcessingService {

    public DocumentResponseDto processText(ProcessTextRequestDto request) {
        if (request == null) throw new IllegalArgumentException("request cannot be null");
        if (request.templateKey() == null) throw new IllegalArgumentException("templateKey is required");
        if (request.rawText() == null || request.rawText().isBlank()) {
            throw new IllegalArgumentException("rawText is required");
        }

        // STUB: later map rawText into multiple sections using the template
        Document document = new Document();
        DocumentSection section = new DocumentSection();
        document.addSection(section);

        return new DocumentResponseDto(
                document.getId(),
                document.getCreatedAt(),
                document.getLastModifiedAt(),
                document.getSections().stream()
                        .map(s -> new DocumentResponseDto.DocumentSectionResponseDto(
                                s.getId(),
                                s.getCreatedAt(),
                                s.getLastModifiedAt()
                        ))
                        .toList()
        );
    }
}
