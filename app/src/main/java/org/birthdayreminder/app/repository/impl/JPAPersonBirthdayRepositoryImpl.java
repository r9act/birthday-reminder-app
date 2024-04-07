package org.birthdayreminder.app.repository.impl;

import org.birthdayreminder.app.entity.PersonBirthdayEntity;
import org.birthdayreminder.app.mapper.PersonBirthdayMapper;
import org.birthdayreminder.app.repository.PersonBirthdayEntityRepository;
import org.birthdayreminder.app.repository.UserEntityRepository;
import org.birthdayreminder.domain.model.DatePeriod;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JPAPersonBirthdayRepositoryImpl implements PersonBirthdayRepository {

    private static final Logger logger = LoggerFactory.getLogger(JPAPersonBirthdayRepositoryImpl.class);

    private final PersonBirthdayEntityRepository birthdayRepo;
    private final PersonBirthdayMapper birthdayMapper;
    private final UserEntityRepository userRepo;

    public JPAPersonBirthdayRepositoryImpl(PersonBirthdayEntityRepository birthdayRepo, PersonBirthdayMapper birthdayMapper, UserEntityRepository userRepo) {
        this.birthdayRepo = birthdayRepo;
        this.birthdayMapper = birthdayMapper;
        this.userRepo = userRepo;
    }

    @Override
    public Integer saveAll(List<PersonBirthday> personBirthdays) {

        var ownerEntity = userRepo.findById(personBirthdays.get(0).user().getId()).orElseThrow(NullPointerException::new);

        List<PersonBirthdayEntity> list = personBirthdays
                .stream()
                .map(birthdayMapper::toEntity)
                .peek(pb -> pb.setUserEntity(ownerEntity))
                .toList();
        logger.info("Number of PersonBirthdays saved: " + list.size());
        return birthdayRepo.saveAll(list).size();

    }

    @Override
    public List<PersonBirthday> getAllByUserId(Long id) {
        var list = birthdayRepo.findAllById(id).stream().map(birthdayMapper::toModel).toList();
        logger.info("Found PersonBirthdays: " + list.size());
        return list;
    }

    @Override
    public List<PersonBirthday> getAllBirthdaysIn(DatePeriod datePeriod) {
        var list = birthdayRepo.findByDateBetween(datePeriod.from(), datePeriod.to()).stream().map(birthdayMapper::toModel).toList();
        logger.info("Found PersonBirthdays: " + list.size());
        return list;
    }

    @Override
    public List<PersonBirthday> findByDatePeriodAndUserId(DatePeriod datePeriod, Long id) {
        var start = datePeriod.from().getDayOfYear();
        var end = datePeriod.to().getDayOfYear();
       return birthdayRepo.findAllByIdAndDateBetween(id, start, end).stream().map(birthdayMapper::toModel).collect(Collectors.toList());
    }
}
