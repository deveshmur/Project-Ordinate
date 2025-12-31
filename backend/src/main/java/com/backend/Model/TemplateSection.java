package com.backend.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "template_sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateSection {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    private String name;

    @Column(name = "order_index")
    private Integer orderIndex;

    @OneToMany(mappedBy = "templateSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentSection> documentSections = new ArrayList<>();
}
