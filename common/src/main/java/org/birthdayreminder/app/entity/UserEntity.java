package org.birthdayreminder.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;import java.util.List;import java.util.Set;

@Table(name = "users")
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long foreignId;
    private String name;
    private Boolean isReminderActive;

	//TODO задать роли
	@ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
        @Column(name = "role")
        private Set<String> roles = new HashSet<>();

    @OneToMany(mappedBy = "userEntity")
    private List<PersonBirthdayEntity> personBirthdayEntities;

}
