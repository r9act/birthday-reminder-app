package org.birthdayreminder.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultFileDTO {

    @JsonProperty(value = "result")
    private FileDTO fileDTO;

    public FileDTO getFileDTO() {
        return fileDTO;
    }
}
