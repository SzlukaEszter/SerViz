package com.codecool.serviceuser.repository;

import com.codecool.serviceuser.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }


    @Test
    void findByUsername() {
        final User user1 = User.builder()
                .username("Water Sommelier of the Year")
                .email("boss@watermail.com")
                .password("aaa")
                .build();
        userRepository.save(user1);

        final User user2 = User.builder()
                .username("Kovács Béla")
                .email("bela@watermail.com")
                .password("bbb")
                .build();
        userRepository.save(user2);

        final Optional<User> user = userRepository.findByUsername("Water Sommelier of the Year");

        assertThat(user)
                .matches(u -> u.get().getEmail().contentEquals("boss@watermail.com"));
    }
}