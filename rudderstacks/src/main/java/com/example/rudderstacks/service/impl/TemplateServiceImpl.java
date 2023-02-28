package com.example.rudderstacks.service.impl;

import com.example.rudderstacks.dao.template.DetailRepository;
import com.example.rudderstacks.dao.template.TemplateRepository;
import com.example.rudderstacks.dto.request.template.FieldRequest;
import com.example.rudderstacks.dto.request.template.OptionsRequest;
import com.example.rudderstacks.dto.request.template.TemplateRequest;
import com.example.rudderstacks.entity.template.Detail;
import com.example.rudderstacks.entity.template.Field;
import com.example.rudderstacks.entity.template.Option;
import com.example.rudderstacks.entity.template.Template;
import com.example.rudderstacks.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.ArrayList;


@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private TemplateRepository templateRepository;

    public Optional<Template> getBySourceType(String type) {
        return templateRepository.findBySourceType(type);
    }

    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public Template saveTemplateWithFields (TemplateRequest templateRequest){
        Template template = new Template();

        template.setSourceType(templateRequest.getType());

        List<Field> fields = new ArrayList<>();
        for(Map.Entry<String, FieldRequest> fieldRequestEntry: templateRequest.getFields().entrySet()) {
            FieldRequest fieldRequest = fieldRequestEntry.getValue();
            Detail detail = new Detail();
            detail.setType(fieldRequest.getType());
            detail.setLabel(fieldRequest.getLabel());
            detail.setPlaceholder(fieldRequest.getPlaceholder());
            detail.setRequired(fieldRequest.getRequired());
            detail.setRegex(fieldRequest.getRegex());
            detail.setRegexErrorMessage(fieldRequest.getRegexErrorMessage());

            List<Option> options = new ArrayList<>();
            if(fieldRequest.getOptionsRequests() != null) {
                for (OptionsRequest optionsRequest : fieldRequest.getOptionsRequests()) {
                    Option option = new Option();
                    option.setLabel(optionsRequest.getLabel());
                    option.setValue(optionsRequest.getValue());
                    option.setDetail(detail);
                    options.add(option);
                }
            }
            detail.setOptions(options);
            detailRepository.save(detail);


            Field field = new Field();
            field.setFieldName(fieldRequestEntry.getKey());
            field.setDetail(detail);
            field.setTemplate(template);
            fields.add(field);

        }
       template.setFields(fields);
       return templateRepository.save(template);
    }

}
