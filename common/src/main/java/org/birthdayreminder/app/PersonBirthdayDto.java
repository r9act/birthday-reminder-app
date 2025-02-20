package org.birthdayreminder.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * @author a.mishkin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonBirthdayDto {
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "surname")
	private String surname;
	@JsonProperty(value = "date")
	private LocalDate date;
	@JsonProperty(value = "owner")
	private UserDto owner;

	public PersonBirthdayDto() {
	}

	public Long getId() {
		return id;
	}

	public PersonBirthdayDto setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public PersonBirthdayDto setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	public PersonBirthdayDto setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public LocalDate getDate() {
		return date;
	}

	public PersonBirthdayDto setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public UserDto getOwner() {
		return owner;
	}

	public PersonBirthdayDto setOwner(UserDto owner) {
		this.owner = owner;
		return this;
	}
}
