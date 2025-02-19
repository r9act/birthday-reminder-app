package org.birthdayreminder.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author a.mishkin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
	@JsonProperty(value = "id")
	private Long androidChatId;
	@JsonProperty(value = "name")
	private String firstName;

	public Long getAndroidChatId() {
		return androidChatId;
	}

	public UserDto setAndroidChatId(Long androidChatId) {
		this.androidChatId = androidChatId;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserDto setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
}

