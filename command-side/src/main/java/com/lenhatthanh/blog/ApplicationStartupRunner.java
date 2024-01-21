package com.lenhatthanh.blog;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.SystemRole;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ApplicationStartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());
    RoleRepositoryInterface roleRepository;
    UserRepositoryInterface userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        logger.info("ApplicationStartupRunner init database !!");

        initRoles();
        initUsers();
    }

    private void initRoles() {
        logger.info("Create system roles !!");

        List<Role> roles = Arrays.asList(
                Role.create(
                        new AggregateId(UniqueIdGenerator.create()),
                        SystemRole.ADMIN,
                        "somebody who has access to all the administration features within a single site"
                ),
                Role.create(
                        new AggregateId(UniqueIdGenerator.create()),
                        SystemRole.AUTHOR,
                        "somebody who can publish and manage their own posts"
                ),
                Role.create(
                        new AggregateId(UniqueIdGenerator.create()),
                        SystemRole.SUBSCRIBER,
                        "somebody who only can view the site"
                )
        );

        roleRepository.saveAll(roles);
    }

    private void initUsers() {
        logger.info("Create system users !!");
        User user = User.create(
                new AggregateId(UniqueIdGenerator.create()),
                "Administrator",
                "lenhatthanh20@gmail.com",
                passwordEncoder.encode("lenhatthanh20")
        );

        Optional<Role> roleUser = roleRepository.findByName(SystemRole.ADMIN);
        roleUser.ifPresent(role -> user.addRole(role.getId()));

        userRepository.save(user);
    }
}
