package com.lenhatthanh.blog;

import com.redis.om.spring.annotations.EnableRedisEnhancedRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRedisEnhancedRepositories(basePackages = "com.lenhatthanh.blog.*")
public class QuerySideApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuerySideApplication.class, args);
	}

}
