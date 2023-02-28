package com.example.rudderstacks.dto.request.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class FieldRequest {
    @JsonProperty("type")
    private String type;

    @JsonProperty("label")
    private String label;


    @JsonProperty("regexErrorMessage")
    private String regexErrorMessage;


    @JsonProperty("placeholder")
    private String placeholder;

    @JsonProperty("regex")
    private String regex;


    @JsonProperty("required")
    private Boolean required;


    @JsonProperty("options")
    private OptionsRequest[] optionsRequests;

}
