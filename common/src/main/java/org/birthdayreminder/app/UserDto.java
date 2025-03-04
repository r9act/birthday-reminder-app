package org.birthdayreminder.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author a.mishkin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "foreignId")
	private Long foreignId;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "isReminderActive")
	private Boolean isReminderActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getForeignId() {
		return foreignId;
	}

	public void setForeignId(Long foreignId) {
		this.foreignId = foreignId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsReminderActive() {
		return isReminderActive;
	}

	public void setIsReminderActive(Boolean isReminderActive) {
		this.isReminderActive = isReminderActive;
	}
}

