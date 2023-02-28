package com.example.rudderstacks.common.util;

import com.example.rudderstacks.common.enums.APIStatus;
import com.example.rudderstacks.common.exception.ApplicationException;
import com.example.rudderstacks.dto.response.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ResponseUtil responseUtil;

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<APIResponse> handleApplicationException(ApplicationException ex, WebRequest request) {

        LOGGER.debug("handleApplicationException", ex);

        ResponseEntity<APIResponse> response;
        if (!(ex.getApiStatus() == APIStatus.OK || ex.getApiStatus() == APIStatus.CREATED)) {
            response = responseUtil.badRequestResponse(ex.getApiStatus(),ex.getData(),ex.getHttpStatus());
        } else {
            response = responseUtil.buildResponse(ex.getApiStatus(), ex.getData(), HttpStatus.OK);
        }
        return response;
    }
}
