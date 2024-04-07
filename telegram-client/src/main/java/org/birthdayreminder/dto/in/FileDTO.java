package org.birthdayreminder.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//This object represents a file ready to be downloaded.
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDTO {

    @JsonProperty(value = "file_path")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }
}
