package com.example.rudderstacks.common.enums;

public enum APIStatus {

    TEMPLATE_DOES_NOT_EXIST(404, "Template does not exist corresponding to given source type"),

    FIELD_VALUE_MISSING(400, "A field is marked as required but is empty"),


    FIELD_IS_NOT_MATCHING_WITH_REGEX(400, "Field value is not matching with th regex"),
    OK(200, "Success"),

    CREATED(201, "Created"),

    ERR_BAD_REQUEST(400, "Bad request"),

    TEMPLATE_ALREADY_EXIST(409, "This template is already exist in database");


    private final int code;
    private final String description;

    private APIStatus(int s, String v) {
        code = s;
        description = v;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
