package org.birthdayreminder.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Table(name = "person_birthdays")
@Entity
@Data
public class PersonBirthdayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

}
