package com.example.rudderstacks.dto.request.sourceConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class SourceConfigRequest {

    @JsonProperty("fields")
    @NotBlank
    private HashMap<String, String> fields ;
}
