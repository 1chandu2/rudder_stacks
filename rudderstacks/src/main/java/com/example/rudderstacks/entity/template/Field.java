package com.example.rudderstacks.entity.template;


import com.example.rudderstacks.entity.sourceConfig.FieldValue;
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
@Table(name="fields")
public class Field {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "field_name")
    private String fieldName;

    @OneToOne
    @JoinColumn(name = "detail_id")
    private Detail detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;


    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FieldValue> fieldRespons = new ArrayList<>();
}
