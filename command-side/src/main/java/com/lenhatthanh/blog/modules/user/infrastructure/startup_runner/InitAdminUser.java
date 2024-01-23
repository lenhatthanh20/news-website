package com.lenhatthanh.blog.modules.user.infrastructure.startup_runner;

import com.lenhatthanh.blog.modules.user.application.usecase.CreateAdminUserUseCase;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitAdminUser implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());
    private CreateAdminUserUseCase createAdminUserUseCase;
    private Environment env;

    @Override
    public void run(String... args) {
        logger.info("Create admin user !!");

        String username = env.getProperty("user.admin.username");
        String email = env.getProperty("user.admin.email");
        String password = env.getProperty("user.admin.password");
        UserDto userDto = new UserDto(username, email, password);
        createAdminUserUseCase.execute(userDto);
    }
}
