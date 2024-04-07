package org.birthdayreminder.app.repository;

import org.birthdayreminder.app.entity.PersonBirthdayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface PersonBirthdayEntityRepository extends JpaRepository<PersonBirthdayEntity, Long> {

    @Query(value = "SELECT person_birthdays.id, owner, person_birthdays.name, person_birthdays.surname, person_birthdays.date FROM person_birthdays " +
            "JOIN users u on u.id = person_birthdays.owner WHERE u.id=?1"
            , nativeQuery = true)
    List<PersonBirthdayEntity> findAllById(Long id);

    List<PersonBirthdayEntity> findByDateBetween(LocalDate start, LocalDate end);

    @Query(value = "SELECT * FROM (SELECT person_birthdays.id, owner, person_birthdays.name, person_birthdays.surname, person_birthdays.date, extract('doy' FROM date)" +
            " as day_of_year FROM person_birthdays JOIN users u on u.id = person_birthdays.owner WHERE u.id=?1 )as foo where day_of_year > ?2" +
            " and day_of_year < ?3",
            nativeQuery = true)
    List<PersonBirthdayEntity> findAllByIdAndDateBetween(Long id, int start, int end);
}
