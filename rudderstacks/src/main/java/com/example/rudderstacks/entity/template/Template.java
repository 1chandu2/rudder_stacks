package com.example.rudderstacks.entity.template;


import com.example.rudderstacks.entity.sourceConfig.SourceConfig;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="templates")
public class Template {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "source_type", unique=true)
    private String sourceType;


    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Field> fields = new ArrayList<>();

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SourceConfig> sourceConfigs = new ArrayList<>();

}
