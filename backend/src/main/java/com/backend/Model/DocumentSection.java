package com.backend.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "document_sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentSection {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_section_id", nullable = false)
    private TemplateSection templateSection;

    @Column(columnDefinition = "text")
    private String content;

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "clarity_score")
    private Double clarityScore;

    @Column(name = "missing_flag")
    private boolean missing;
}
