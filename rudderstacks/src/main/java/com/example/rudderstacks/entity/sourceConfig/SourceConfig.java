package com.example.rudderstacks.entity.sourceConfig;

import com.example.rudderstacks.entity.template.Template;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="source_configs")
public class SourceConfig {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;

    @OneToMany(mappedBy = "sourceConfig", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FieldValue> fieldValues = new ArrayList<>();

}
