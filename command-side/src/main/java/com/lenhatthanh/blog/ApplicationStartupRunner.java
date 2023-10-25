package com.lenhatthanh.blog;

import com.lenhatthanh.blog.domain.Role;
import com.lenhatthanh.blog.domain.SystemRole;
import com.lenhatthanh.blog.domain.repository.RoleRepositoryInterface;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ApplicationStartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());
    RoleRepositoryInterface roleRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("ApplicationStartupRunner init database !!");
        logger.info("Create system roles:  !!");

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
}