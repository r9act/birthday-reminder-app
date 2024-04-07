package org.birthdayreminder.app;

import org.birthdayreminder.app.repository.PersonBirthdayEntityRepository;
import org.birthdayreminder.app.repository.UserEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AppApplicationTests {
	@MockBean
	UserEntityRepository userEntityRepository;
	@MockBean
	PersonBirthdayEntityRepository personBirthdayEntityRepository;

	@Test
	void contextLoads() {
	}
}
