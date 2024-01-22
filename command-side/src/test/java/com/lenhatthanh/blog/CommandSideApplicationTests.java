package com.lenhatthanh.blog;

import com.lenhatthanh.blog.modules.user.infrastructure.rest_api.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
class CommandSideApplicationTests {
	@Autowired
	private UserController controller;

	/**
	 * Don't need to run this bean when startup application in test environment
	 */
	@MockBean
	ApplicationStartupRunner applicationStartupRunner;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
