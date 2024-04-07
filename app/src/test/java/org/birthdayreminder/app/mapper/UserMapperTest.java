package org.birthdayreminder.app.mapper;

import org.birthdayreminder.app.entity.UserEntity;
import org.birthdayreminder.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    public void setup() {
        userMapper = new UserMapperImpl();
    }
    
    @Test
    public void shouldMapEntityToModel() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setChatId(1234L);
        userEntity.setName("Artem");
        userEntity.setIsReminderActive(false);
        //when
        User user = userMapper.toModel(userEntity);
        //then
        var expectedName = "Artem";
        assertNotNull(user.getName());
        assertEquals(expectedName, user.getName());
        assertEquals(userEntity.getChatId(), user.getChatId());
        System.out.println(user.getName());
    }

    @Test
    public void shouldMapModelToEntity() {
        //given
        User user = new User(12345L, "Artem", false);
        //when
        UserEntity userEntity = userMapper.toEntity(user);
        //then
        assertEquals("Artem", userEntity.getName());
        assertEquals(12345L, userEntity.getChatId());
        assertEquals(false, userEntity.getIsReminderActive());
    }
}
