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
	@JsonProperty(value = "androidId")
	private Long androidId;
	@JsonProperty(value = "name")
	private String firstName;

	public Long getId() {
		return id;
	}

	public UserDto setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getAndroidId() {
		return androidId;
	}

	public UserDto setAndroidId(Long androidId) {
		this.androidId = androidId;
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

