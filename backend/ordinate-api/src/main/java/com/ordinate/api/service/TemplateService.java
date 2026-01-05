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
        template.addSection(new TemplateSection("Agenda", 1));
        template.addSection(new TemplateSection("Discussion", 2));
        template.addSection(new TemplateSection("Decisions", 3));
        template.addSection(new TemplateSection("Action Items", 4));
        return template;
    }

    private Template studentLectureNotes() {
        Template template = new Template(TemplateKey.STUDENT_LECTURE_NOTES);
        template.addSection(new TemplateSection("Topic / Title", 1));
        template.addSection(new TemplateSection("Key Concepts", 2));
        template.addSection(new TemplateSection("Examples", 3));
        template.addSection(new TemplateSection("Questions / Confusions", 4));
        return template;
    }

    private Template healthcareSoapNote() {
        Template template = new Template(TemplateKey.HEALTHCARE_SOAP_NOTE);
        template.addSection(new TemplateSection("Subjective", 1));
        template.addSection(new TemplateSection("Objective", 2));
        template.addSection(new TemplateSection("Assessment", 3));
        template.addSection(new TemplateSection("Plan", 4));
        return template;
    }
}
