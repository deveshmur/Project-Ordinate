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

        String raw = request.rawText().trim();
        String[] sentences = raw.split("(?<=[.!?])\\s+");

        StringBuilder discussion = new StringBuilder();
        StringBuilder decisions = new StringBuilder();
        StringBuilder actionItems = new StringBuilder();

        for (String s : sentences) {
            String lower = s.toLowerCase();

            if (lower.contains("decide") || lower.contains("decision")) {
                decisions.append(s).append("\n");
            } else if (lower.contains("action") || lower.contains("todo") || lower.contains("assign")) {
                actionItems.append(s).append("\n");
            } else {
                discussion.append(s).append("\n");
            }
        }

        document.setSectionContent(2, discussion.toString().trim());
        document.setSectionContent(3, decisions.toString().trim());
        document.setSectionContent(4, actionItems.toString().trim());

        return new DocumentResponseDto(
                document.getId(),
                document.getCreatedAt(),
                document.getLastModifiedAt(),
                document.getSections().stream()
                    .map(s -> new DocumentResponseDto.DocumentSectionResponseDto(
                            s.getId(),
                            s.getCreatedAt(),
                            s.getLastModifiedAt(),
                            s.getName(),
                            s.getOrderIndex(),
                            s.getContent()
                    ))
                    .toList()
        );
    }
}
