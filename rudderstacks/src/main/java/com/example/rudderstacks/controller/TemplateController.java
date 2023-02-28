package com.example.rudderstacks.controller;

import com.example.rudderstacks.common.enums.APIStatus;
import com.example.rudderstacks.common.exception.ApplicationException;
import com.example.rudderstacks.common.util.ResponseUtil;
import com.example.rudderstacks.dto.request.template.TemplateRequest;
import com.example.rudderstacks.dto.response.APIResponse;
import com.example.rudderstacks.dto.response.template.TemplateResponse;
import com.example.rudderstacks.mapper.template.TemplateMapper;
import com.example.rudderstacks.service.impl.TemplateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rudderstacks/v1")
@CrossOrigin("http://localhost:3000")
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);
    @Autowired
    private TemplateServiceImpl templateService;

    @Autowired private ResponseUtil responseUtil;

    @Autowired private TemplateMapper templateMapper;


    /**
     * api to check health of application.
     * @return if we are getting call on application successfully then returning just success response.
     */
    @GetMapping("/health")
    public ResponseEntity<APIResponse> healthCheck() {
        logger.info("received call on application successfully");
        return responseUtil.buildResponse(APIStatus.OK, APIStatus.OK, HttpStatus.OK);
    }

    /**
     * api to get all sources present in database.
     * @return returning list of sources in data field of response.
     */
    @GetMapping("/sources")
    public ResponseEntity<APIResponse> getAllSource() {
        logger.info("received call for get all sources");
        List<String> sources = templateService.findAll()
                .stream()
                .map(template -> template.getSourceType())
                .collect(Collectors.toList());

        logger.info("successfully fetched templates from database");
        return responseUtil.buildResponse(APIStatus.OK, sources, HttpStatus.OK);
    }


    /**
     * api to get template corresponding to a source type.
     * @return returning templateResponse in data field of response.
     */
    @GetMapping("/templates/{type}")
    public ResponseEntity<APIResponse> getTemplateFromSourceType(@PathVariable( name = "type", required = true) String type) {
        logger.info("received call for get template corresponding to {} type", type);

        return templateService
                .getBySourceType(type)
                .map(template -> templateMapper.mapToTemplateResponse(template))
                .map(res -> responseUtil.buildResponse(APIStatus.OK, res, HttpStatus.OK))
                .orElseThrow(() -> new ApplicationException(APIStatus.TEMPLATE_DOES_NOT_EXIST));
    }

    /**
     * api to get all template  present in database.
     * @return returning list of templateResponse in data field of response.
     */
    @GetMapping("/templates")
    public ResponseEntity<APIResponse> getAllTemplates() {
        logger.info("received call for get all templates");

        List<TemplateResponse> templateResponses = templateService.findAll()
                .stream()
                .map(t -> templateMapper.mapToTemplateResponse(t))
                .collect(Collectors.toList());

        return responseUtil.buildResponse(APIStatus.OK, templateResponses, HttpStatus.OK);
    }


    /**
     * api to  create new template.
     * @return returning success response when successfully creating the template.
     */
    @PostMapping("/templates")
    public ResponseEntity<APIResponse> createTemplate(@RequestBody TemplateRequest templateRequest) {

        logger.info("received new template creation request : {}", templateRequest);

        if (templateService.getBySourceType(templateRequest.getType()).isPresent()){
            logger.info("template corresponding to this source already exists:{}", templateRequest.getType());
            throw new ApplicationException(APIStatus.TEMPLATE_ALREADY_EXIST);
        }

        templateService.saveTemplateWithFields(templateRequest);
        logger.info("successfully saved the template corresponding to {} type", templateRequest.getType());
        return responseUtil.createResponse(APIStatus.CREATED, HttpStatus.CREATED);
    }
}
