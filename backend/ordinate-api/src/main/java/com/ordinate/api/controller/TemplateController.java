package com.ordinate.api.controller;

import com.ordinate.api.domain.Template;
import com.ordinate.api.dto.TemplateResponseDto;
import com.ordinate.api.service.TemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/software-meeting")
    public TemplateResponseDto getSoftwareMeetingTemplate() {
        Template template = templateService.getSoftwareTeamMeetingTemplate();

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
