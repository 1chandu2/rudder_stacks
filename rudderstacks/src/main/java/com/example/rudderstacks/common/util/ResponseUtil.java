package com.example.rudderstacks.common.util;

import com.example.rudderstacks.common.enums.APIStatus;
import com.example.rudderstacks.dto.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseUtil {

    private APIResponse _successAPIResponse(APIStatus apiStatus, Object data) {
        return new APIResponse(apiStatus, data);
    }

    public ResponseEntity<APIResponse> buildResponse(APIStatus apiStatus, Object data, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(_successAPIResponse(apiStatus, data));
    }

    private APIResponse _createAPIResponse(APIStatus apiStatus) {
        return new APIResponse(apiStatus);
    }

    public ResponseEntity<APIResponse> createResponse(APIStatus apiStatus, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(_createAPIResponse(apiStatus));
    }

    public ResponseEntity<APIResponse> badRequestResponse(APIStatus apiStatus, List<Constant.ParamError> errors, HttpStatus httpStatus) {

        Map<String, String> errMap = null;

        if (errors != null) {

            errMap = new HashMap<>();
            for (Constant.ParamError error : errors) {
                errMap.put(error.getName(), error.getDesc());
            }
        }

        if (apiStatus == null) {
            apiStatus = APIStatus.ERR_BAD_REQUEST;
        }
        if (httpStatus == null) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return buildResponse(apiStatus, errMap, httpStatus);
    }
}
