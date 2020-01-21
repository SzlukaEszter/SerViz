package com.codecool.serviceuser.service;

import com.codecool.serviceuser.entity.User;
import com.codecool.serviceuser.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
       userRepository.deleteAll();
    }

    @Test
    void getAllUsers() {
        final User user1 = User.builder()
                .username("Water Sommelier of the Year")
                .email("boss@watermail.com")
                .password("aaa")
                .build();
        userService.saveUser(user1);

        final User user2 = User.builder()
                .username("Kovács Béla")
                .email("bela@watermail.com")
                .password("bbb")
                .build();
        userService.saveUser(user2);

        List<User> users = userService.getAllUsers();

        assertThat(users).hasSize(2);
    }

    @Test
    void getUserById() {
        final User user1 = User.builder()
                .username("Water Sommelier of the Year")
                .email("boss@watermail.com")
                .password("aaa")
                .build();
        Long id = userService.saveUser(user1);

        User user = userService.getUserById(id);

        assertThat(user)
               .matches(u -> u.getEmail().equals("boss@watermail.com"));
    }

    @Test
    void getUserByName() {
        final User user1 = User.builder()
                .username("Water Sommelier of the Year")
                .email("boss@watermail.com")
                .password("aaa")
                .build();
        userService.saveUser(user1);

        final User user2 = User.builder()
                .username("Kovács Béla")
                .email("bela@watermail.com")
                .password("bbb")
                .build();
        userService.saveUser(user2);

        User user = userService.getUserByUsername("Kovács Béla");

        assertThat(user)
                .matches(u -> u.getEmail().contentEquals("bela@watermail.com"));
    }
}