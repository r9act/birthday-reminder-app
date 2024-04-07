package org.birthdayreminder.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDTO {

    @JsonProperty(value = "result")
    private List<ResultDTO> resultDTO;

    public List<ResultDTO> getResultDTO() {
        return resultDTO;
    }

}
