package com.ordinate.api.service;

import com.ordinate.api.domain.Template;
import com.ordinate.api.domain.TemplateKey;
import com.ordinate.api.domain.TemplateSection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
    public Template getSoftwareTeamMeetingTemplate() {

        Template template = new Template(TemplateKey.SOFTWARE_TEAM_MEETING);

        template.addSection(new TemplateSection(1)); // Agenda
        template.addSection(new TemplateSection(2)); // Discussion
        template.addSection(new TemplateSection(3)); // Decisions
        template.addSection(new TemplateSection(4)); // Action Items

        return template;
    }
}
