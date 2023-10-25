package com.lenhatthanh.blog;

import com.lenhatthanh.blog.domain.Role;
import com.lenhatthanh.blog.domain.SystemRole;
import com.lenhatthanh.blog.domain.User;
import com.lenhatthanh.blog.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.domain.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ApplicationStartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());
    RoleRepositoryInterface roleRepository;
    UserRepositoryInterface userRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("ApplicationStartupRunner init database !!");

        initRoles();
        initUsers();
    }

    private void initRoles() {
        logger.info("Create system roles !!");

        List<Role> roles = Arrays.asList(
                new Role(
                        UUID.randomUUID().toString(),
                        SystemRole.ADMIN,
                        "somebody who has access to all the administration features within a single site"
                ),
                new Role(
                        UUID.randomUUID().toString(),
                        SystemRole.AUTHOR,
                        "somebody who can publish and manage their own posts"
                )
        );

        roleRepository.saveAll(roles);
    }

    private void initUsers() {
        logger.info("Create system users !!");
        User user = new User(
                UUID.randomUUID().toString(),
                "Administrator",
                "lenhatthanh20@gmail.com",
                "lenhatthanh20"
        );

        Optional<Role> roleUser = roleRepository.findByName(SystemRole.ADMIN);
        roleUser.ifPresent(user::addRole);

        userRepository.save(user);
    }
}
