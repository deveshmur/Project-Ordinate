package com.ordinate.api.service;

import com.ordinate.api.domain.Document;
import com.ordinate.api.domain.DocumentSection;
import com.ordinate.api.domain.Template;
import com.ordinate.api.dto.DocumentResponseDto;
import com.ordinate.api.dto.ProcessTextRequestDto;
import org.springframework.stereotype.Service;

@Service
public class TextProcessingService {

    private final TemplateService templateService;

    public TextProcessingService(TemplateService templateService) {
        this.templateService = templateService;
    }

    public DocumentResponseDto processText(ProcessTextRequestDto request) {
        Template template = templateService.getTemplate(request.templateKey());

        Document document = new Document();

        template.getSections().forEach(ts ->
                document.addSection(new DocumentSection(ts.getName(), ts.getOrderIndex()))
        );

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
