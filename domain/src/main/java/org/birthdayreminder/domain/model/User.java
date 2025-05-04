package org.birthdayreminder.domain.model;

import lombok.Data;import java.util.HashSet;import java.util.Set;

@Data
public class User {
    private Long id;
    private Long foreignId;
    private String name;
    private Boolean isReminderActive;
	//TODO
 	private Set<String> roles = new HashSet<>();
	 private String username;
	 private String password;

    public User() {
    }

    public User(Long foreignId, String name, Boolean isReminderActive) {
        this.foreignId = foreignId;
        this.name = name;
        this.isReminderActive = isReminderActive;
    }

    public User(Long id, Long foreignId, String name, Boolean isReminderActive) {
        this.id = id;
        this.foreignId = foreignId;
        this.name = name;
        this.isReminderActive = isReminderActive;
    }
}
