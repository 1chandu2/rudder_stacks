package com.example.rudderstacks.entity.sourceConfig;

import com.example.rudderstacks.entity.template.Field;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="field_values")
public class FieldValue {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "field_value")
    private String fieldValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_config_id")
    private SourceConfig sourceConfig;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field field;
}
