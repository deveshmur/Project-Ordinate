package com.ordinate.api.service;

import com.ordinate.api.domain.Template;
import com.ordinate.api.domain.TemplateKey;
import com.ordinate.api.domain.TemplateSection;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    public Template getTemplate(TemplateKey key) {
        return switch (key) {
            case SOFTWARE_TEAM_MEETING -> softwareTeamMeeting();
            case STUDENT_LECTURE_NOTES -> studentLectureNotes();
            case HEALTHCARE_SOAP_NOTE -> healthcareSoapNote();
        };
    }

    public java.util.List<Template> getAllTemplates() {
        return java.util.List.of(
                getTemplate(TemplateKey.SOFTWARE_TEAM_MEETING),
                getTemplate(TemplateKey.STUDENT_LECTURE_NOTES),
                getTemplate(TemplateKey.HEALTHCARE_SOAP_NOTE)
        );
    }

    private Template softwareTeamMeeting() {
        Template template = new Template(TemplateKey.SOFTWARE_TEAM_MEETING);
        template.addSection(new TemplateSection(1)); // Agenda
        template.addSection(new TemplateSection(2)); // Discussion
        template.addSection(new TemplateSection(3)); // Decisions
        template.addSection(new TemplateSection(4)); // Action Items
        return template;
    }

    private Template studentLectureNotes() {
        Template template = new Template(TemplateKey.STUDENT_LECTURE_NOTES);
        template.addSection(new TemplateSection(1)); // Topic / Title
        template.addSection(new TemplateSection(2)); // Key Concepts
        template.addSection(new TemplateSection(3)); // Examples
        template.addSection(new TemplateSection(4)); // Questions / Confusions
        return template;
    }

    private Template healthcareSoapNote() {
        Template template = new Template(TemplateKey.HEALTHCARE_SOAP_NOTE);
        template.addSection(new TemplateSection(1)); // Subjective
        template.addSection(new TemplateSection(2)); // Objective
        template.addSection(new TemplateSection(3)); // Assessment
        template.addSection(new TemplateSection(4)); // Plan
        return template;
    }
}
