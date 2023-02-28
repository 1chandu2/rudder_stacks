package com.example.rudderstacks.mapper.template;

import com.example.rudderstacks.dao.template.FieldRepository;
import com.example.rudderstacks.dao.template.OptionRepository;
import com.example.rudderstacks.dto.response.template.FieldResponse;
import com.example.rudderstacks.dto.response.template.OptionsResponse;
import com.example.rudderstacks.dto.response.template.TemplateResponse;
import com.example.rudderstacks.entity.template.Detail;
import com.example.rudderstacks.entity.template.Field;
import com.example.rudderstacks.entity.template.Option;
import com.example.rudderstacks.entity.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TemplateMapper {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private OptionRepository optionRepository;

   public TemplateResponse mapToTemplateResponse(Template template) {
        TemplateResponse templateResponse = new TemplateResponse();
        templateResponse.setType(template.getSourceType());
        HashMap<String, FieldResponse> fieldResponses = new HashMap<>();

        for(Field field : fieldRepository.findByTemplate(template)) {
            Detail detail = field.getDetail();
            FieldResponse fieldResponse = new FieldResponse();
            fieldResponse.setType(detail.getType());
            fieldResponse.setLabel(detail.getLabel());
            fieldResponse.setRequired(detail.getRequired());
            fieldResponse.setRegex(detail.getRegex());
            fieldResponse.setRegexErrorMessage(detail.getRegexErrorMessage());
            fieldResponse.setPlaceholder(detail.getPlaceholder());

            OptionsResponse[] optionsResponses = new OptionsResponse[detail.getOptions().size()];
            int index = 0;
            for(Option option : optionRepository.findByDetail(detail)) {
                OptionsResponse optionsResponse = new OptionsResponse();
                optionsResponse.setLabel(option.getLabel());
                optionsResponse.setValue(option.getValue());
                optionsResponses[index++] = optionsResponse;
            }

            fieldResponse.setOptionsResponses(optionsResponses);
            fieldResponses.put(field.getFieldName(), fieldResponse);

        }
        templateResponse.setFields(fieldResponses);
        return templateResponse;
   }
}
