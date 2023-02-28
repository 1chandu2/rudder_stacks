package com.example.rudderstacks.mapper.sourceConfig;

import com.example.rudderstacks.dao.sourceConfig.FieldValueRepository;
import com.example.rudderstacks.dao.template.FieldRepository;
import com.example.rudderstacks.dto.response.sourceConfig.SourceConfigResponse;
import com.example.rudderstacks.entity.sourceConfig.FieldValue;
import com.example.rudderstacks.entity.sourceConfig.SourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class SourceConfigMapper {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FieldValueRepository fieldValueRepository;

    public SourceConfigResponse mapToTemplateResponse(SourceConfig sourceConfig) {
        SourceConfigResponse sourceConfigResponse = new SourceConfigResponse();
        HashMap<String, String> fieldValuesMap = new HashMap<>();
        List<FieldValue> fieldValues = fieldValueRepository.findBySourceConfig(sourceConfig);
        for(FieldValue fieldValue : fieldValues) {
            fieldValuesMap.put(fieldRepository.findById(fieldValue.getField().getId()).get().getFieldName(), fieldValue.getFieldValue());
        }
        sourceConfigResponse.setFieldValues(fieldValuesMap);
        return sourceConfigResponse;
    }
}
