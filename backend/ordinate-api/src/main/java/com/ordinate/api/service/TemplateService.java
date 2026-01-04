package com.ordinate.api.service;

import com.ordinate.api.domain.Template;
import com.ordinate.api.domain.TemplateSection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
   public Template getSoftwareTeamMeetingTemplate() {

        Template template = new Template();

        List<TemplateSection> sections = List.of(
                new TemplateSection(1), // Agenda
                new TemplateSection(2), // Discussion
                new TemplateSection(3), // Decisions
                new TemplateSection(4)  // Action Items
        );
        return template;
    } 
}
