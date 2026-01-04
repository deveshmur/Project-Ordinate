package com.ordinate.api.controller;

import com.ordinate.api.domain.Template;
import com.ordinate.api.domain.TemplateKey;
import com.ordinate.api.dto.TemplateResponseDto;
import com.ordinate.api.service.TemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public java.util.List<TemplateResponseDto> listTemplates() {
        return templateService.getAllTemplates().stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{key}")
    public TemplateResponseDto getTemplateByKey(@PathVariable TemplateKey key) {
        return toDto(templateService.getTemplate(key));
    }

    @GetMapping("/software-meeting")
    public TemplateResponseDto getSoftwareMeetingTemplate() {
        return toDto(templateService.getTemplate(TemplateKey.SOFTWARE_TEAM_MEETING));
    }

    private TemplateResponseDto toDto(Template template) {
        return new TemplateResponseDto(
                template.getId(),
                template.getKey(),
                template.getCreatedAt(),
                template.getLastModifiedAt(),
                template.getSections().stream()
                        .map(s -> new TemplateResponseDto.TemplateSectionResponseDto(
                                s.getId(),
                                s.getCreatedAt(),
                                s.getOrderIndex()
                        ))
                        .toList()
        );
    }
}
