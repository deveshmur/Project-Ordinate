package com.ordinate.api.service;

import com.ordinate.api.domain.Document;
import com.ordinate.api.domain.DocumentSection;
import com.ordinate.api.dto.DocumentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    public DocumentResponseDto createSampleDocument() {

        Document document = new Document();

        DocumentSection section = new DocumentSection("Sample Section", 1);
        document.addSection(section);

        return mapToDto(document);
    }

    private DocumentResponseDto mapToDto(Document document) {
        var sectionDtos = document.getSections().stream()
                .map(s -> new DocumentResponseDto.DocumentSectionResponseDto(
                        s.getId(),
                        s.getCreatedAt(),
                        s.getLastModifiedAt(),
                        s.getName(),
                        s.getOrderIndex(),
                        s.getContent(),
                        confidenceFromContent(s.getContent())
                ))
                .toList();

        var missing = document.getSections().stream()
                .filter(s -> s.getContent() == null || s.getContent().isBlank())
                .map(com.ordinate.api.domain.DocumentSection::getName)
                .toList();

        return new DocumentResponseDto(
                document.getId(),
                document.getCreatedAt(),
                document.getLastModifiedAt(),
                sectionDtos,
                missing
        );
    }
    
    private double confidenceFromContent(String content) {
        if (content == null || content.isBlank()) return 0.0;

        int len = content.trim().length();
        double score = Math.min(1.0, len / 120.0);
        return Math.round(score * 100.0) / 100.0;
    }
}