package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DBConfig {
    @Bean
    CommandLineRunner initRoles(RoleService roleService, UserService userService) {
        return args -> {
            List<Role> adminRoles = new ArrayList<>();
            List<Role> userRoles = new ArrayList<>();

            Role adminRole = new Role();
            Role userRole = new Role();

            User admin = new User();
            User user = new User();

            adminRole.setRoleName("ADMIN");
            userRole.setRoleName("USER");
            adminRoles.add(adminRole);
            adminRoles.add(userRole);
            userRoles.add(userRole);
            roleService.addRole(adminRole);
            roleService.addRole(userRole);

            admin.setName("admin");
            admin.setLastName("adminov");
            admin.setAge(100);
            admin.setPassword("admin");
            admin.setEmail("admin@mail.ru");
            admin.setRoles(adminRoles);
            userService.addUser(admin);

            user.setName("user");
            user.setLastName("userov");
            user.setAge(10);
            user.setPassword("user");
            user.setEmail("user@mail.ru");
            user.setRoles(userRoles);
            userService.addUser(user);
        };
    }
}
