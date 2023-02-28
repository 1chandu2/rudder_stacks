package com.example.rudderstacks.controller;

import com.example.rudderstacks.common.enums.APIStatus;
import com.example.rudderstacks.common.exception.ApplicationException;
import com.example.rudderstacks.common.util.ResponseUtil;
import com.example.rudderstacks.dao.sourceConfig.SourceConfigRepository;
import com.example.rudderstacks.dto.request.sourceConfig.SourceConfigRequest;
import com.example.rudderstacks.dto.response.APIResponse;
import com.example.rudderstacks.dto.response.template.FieldResponse;
import com.example.rudderstacks.dto.response.template.TemplateResponse;
import com.example.rudderstacks.entity.template.Template;
import com.example.rudderstacks.mapper.sourceConfig.SourceConfigMapper;
import com.example.rudderstacks.mapper.template.TemplateMapper;
import com.example.rudderstacks.service.SourceConfigService;
import com.example.rudderstacks.service.impl.TemplateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rudderstacks/v1")
@CrossOrigin("http://localhost:3000")
public class SourceConfigController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);
    @Autowired
    private TemplateServiceImpl templateService;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private SourceConfigMapper sourceConfigMapper;

    @Autowired
    private SourceConfigService sourceConfigService;

    @Autowired
    private SourceConfigRepository sourceConfigRepository;

    @Autowired
    ResponseUtil responseUtil;

    /**
     * api to add configuration corresponding to a particular source type.
     * @return returning success response when successfully add the configuration in database.
     */
    @PostMapping("/sourceConfigs/{type}")
    public ResponseEntity<APIResponse> addConfig(@PathVariable( name = "type", required = false) String type,
                                                 @RequestBody SourceConfigRequest sourceConfigRequest) {
        logger.info("received call for save the source configuration: {}", sourceConfigRequest);
        Optional<Template> templateOptional = templateService.getBySourceType(type);

        if(!templateOptional.isPresent()) {
            logger.info("There is no template corresponding to {} type", type);
            throw new ApplicationException(APIStatus.TEMPLATE_DOES_NOT_EXIST);
        }

        TemplateResponse templateResponse = templateMapper.mapToTemplateResponse(templateOptional.get());

        for(Map.Entry<String, FieldResponse> fieldResponseEntry : templateResponse.getFields().entrySet()) {
            String field_name = fieldResponseEntry.getKey();
            FieldResponse fieldResponse = fieldResponseEntry.getValue();
            if(fieldResponse.getRequired()) {
                if (sourceConfigRequest.getFields().get(field_name) == null || sourceConfigRequest.getFields().get(field_name).isBlank()) {
                    throw new ApplicationException(APIStatus.FIELD_VALUE_MISSING);
                }
            }
            if(fieldResponse.getRegex() != null) {
                if(!(Pattern.compile(fieldResponse.getRegex())
                            .matcher(sourceConfigRequest.getFields().get(field_name))
                            .matches())) {
                    throw new ApplicationException(APIStatus.FIELD_IS_NOT_MATCHING_WITH_REGEX);
                }
            }
        }

        sourceConfigService.saveSourceConfig(sourceConfigRequest, type);
        return responseUtil.createResponse(APIStatus.CREATED, HttpStatus.CREATED);

    }

    /**
     * api to get the list configuration of particular source type
     * @return returning list of configs corresponding to source type in data field of response.
     */
    @GetMapping("/sourceConfigs/{type}")
    public ResponseEntity<APIResponse> getTemplateFromSourceType(@PathVariable( name = "type", required = true) String type) {
        logger.info("received call for get configs corresponding to {} type", type);

        List<HashMap<String, String>> sourceConfigsResponses = templateService.getBySourceType(type)
                .map(t -> sourceConfigRepository.findByTemplate(t))
                .map(configs -> configs.stream()
                        .map(config -> sourceConfigMapper.mapToTemplateResponse(config).getFieldValues())
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ApplicationException(APIStatus.TEMPLATE_DOES_NOT_EXIST));
        return responseUtil.buildResponse(APIStatus.OK, sourceConfigsResponses, HttpStatus.OK);
    }

}
