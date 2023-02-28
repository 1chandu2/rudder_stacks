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
public class OptionsRequest {
    @JsonProperty("label")
    private String label;

    @JsonProperty("value")
    private String value;
}
