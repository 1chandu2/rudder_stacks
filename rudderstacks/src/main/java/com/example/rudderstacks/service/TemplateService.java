package com.example.rudderstacks.service;

import com.example.rudderstacks.dto.request.template.TemplateRequest;
import com.example.rudderstacks.entity.template.Template;

import java.util.List;
import java.util.Optional;

public interface TemplateService {

    Optional<Template> getBySourceType(String type);
    List<Template> findAll();
    Template saveTemplateWithFields(TemplateRequest templateRequest);
}
