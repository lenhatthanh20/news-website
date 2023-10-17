package com.lenhatthanh.blog;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import com.redis.om.spring.annotations.EnableRedisEnhancedRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "com.lenhatthanh.blog.*")
public class QuerySideApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuerySideApplication.class, args);
	}

}
