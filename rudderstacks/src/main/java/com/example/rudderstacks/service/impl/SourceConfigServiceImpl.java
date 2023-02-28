package com.example.rudderstacks.service.impl;

import com.example.rudderstacks.dao.template.FieldRepository;
import com.example.rudderstacks.dao.sourceConfig.SourceConfigRepository;
import com.example.rudderstacks.dto.request.sourceConfig.SourceConfigRequest;
import com.example.rudderstacks.entity.sourceConfig.FieldValue;
import com.example.rudderstacks.entity.sourceConfig.SourceConfig;
import com.example.rudderstacks.entity.template.Field;
import com.example.rudderstacks.entity.template.Template;
import com.example.rudderstacks.service.SourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SourceConfigServiceImpl implements SourceConfigService {
    @Autowired
    private SourceConfigRepository sourceConfigRepository;

    @Autowired
    TemplateServiceImpl templateService;

    @Autowired
    FieldRepository fieldRepository;

    @Override
    public SourceConfig saveSourceConfig(SourceConfigRequest sourceConfigRequest, String type) {
        SourceConfig sourceConfig = new SourceConfig();

        Optional<Template> templateOptional = templateService.getBySourceType(type);
        Template template = templateOptional.get();
        List<Field> fields = fieldRepository.findByTemplate(template);

        HashMap<String, Field> fieldHashMap = new HashMap<>();
        for(Field field: fields) {
            fieldHashMap.put(field.getFieldName(), field);
        }

        List<FieldValue> fieldValues = new ArrayList<>();
        for(Map.Entry<String, String> entry: sourceConfigRequest.getFields().entrySet()) {
            String field_name = entry.getKey();
            String field_value = entry.getValue();

            Field field = fieldHashMap.get(field_name);

            FieldValue fieldValue = new FieldValue();
            fieldValue.setField(field);
            fieldValue.setFieldValue(field_value);
            fieldValue.setSourceConfig(sourceConfig);
            fieldValues.add(fieldValue);
        }
        sourceConfig.setTemplate(template);
        sourceConfig.setFieldValues(fieldValues);

        return sourceConfigRepository.save(sourceConfig);
    }
}
