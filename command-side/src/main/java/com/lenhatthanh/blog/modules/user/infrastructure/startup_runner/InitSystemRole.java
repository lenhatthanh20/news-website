package com.lenhatthanh.blog.modules.user.infrastructure.startup_runner;

import com.lenhatthanh.blog.modules.user.application.usecase.CreateSystemRoles;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class InitSystemRole implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());
    private CreateSystemRoles createSystemRolesUseCase;
    private Environment env;

    @Override
    public void run(String... args) {
        logger.info("Create system roles !!");

        String adminRoleName = env.getProperty("systemRole.admin.name");
        String adminRoleDescription = env.getProperty("systemRole.admin.description");
        String authorRoleName = env.getProperty("role.author.name");
        String authorRoleDescription = env.getProperty("systemRole.author.description");
        String subscriberRoleName = env.getProperty("role.subscriber.name");
        String subscriberRoleDescription = env.getProperty("systemRole.subscriber.description");

        RoleDto AdminRoleDto = new RoleDto(adminRoleName, adminRoleDescription);
        RoleDto AuthorRoleDto = new RoleDto(authorRoleName, authorRoleDescription);
        RoleDto SubscriberRoleDto = new RoleDto(subscriberRoleName, subscriberRoleDescription);

        List<RoleDto> roleDtoList = new ArrayList<>();
        roleDtoList.add(AdminRoleDto);
        roleDtoList.add(AuthorRoleDto);
        roleDtoList.add(SubscriberRoleDto);

        createSystemRolesUseCase.execute(roleDtoList);
    }
}
