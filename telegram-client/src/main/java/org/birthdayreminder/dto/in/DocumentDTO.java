package org.birthdayreminder.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDTO {
    @JsonProperty(value = "file_id")
    private String fileId;
    @JsonProperty(value = "file_name")
    private String fileName;

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }
}
