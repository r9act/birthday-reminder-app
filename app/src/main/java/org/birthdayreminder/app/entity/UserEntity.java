package org.birthdayreminder.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "users")
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String name;
    private Boolean isReminderActive;

    @OneToMany(mappedBy = "userEntity")
    private List<PersonBirthdayEntity> personBirthdayEntities;

}
