package com.ordinate.api.controller;

import com.ordinate.api.domain.Document;
import com.ordinate.api.domain.DocumentSection;
import com.ordinate.api.dto.DocumentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    @GetMapping("/sample")
    public DocumentResponseDto getSampleDocument() {

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
