package com.example.rudderstacks.entity.template;

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
@Table(name="details")
public class Detail {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "label")
    private String label;


    @Column(name = "regex_error_message")
    private String regexErrorMessage;


    @Column(name = "regex")
    private String regex;


    @Column(name = "required")
    private Boolean required;

    @Column(name = "placeholder")
    private String placeholder;

    @OneToOne(mappedBy = "detail")
    private Field field;

    @OneToMany(mappedBy = "detail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();


}
