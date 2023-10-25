package com.lenhatthanh.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
class CommandSideApplicationTests {

	@Test
	void contextLoads() {
	}

}
