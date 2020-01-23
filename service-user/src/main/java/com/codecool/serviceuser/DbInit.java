package com.codecool.serviceuser;

import com.codecool.serviceuser.entity.User;
import com.codecool.serviceuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DbInit {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void postConstruct() {
        final User user1 = User.builder()
                .username("Water Sommelier of the Year")
                .email("szluka.eszter@gmail.com")
                .password("aaa")
                .roles(Arrays.asList("ROLE_USER"))
                .build();
        userRepository.save(user1);

        final User user2 = User.builder()
                .username("Kovács Béla")
                .password("bbb")
                .email("bela@watermail.com")
                .roles(Arrays.asList("ROLE_USER"))
                .build();
        userRepository.save(user2);

        final User user3 = User.builder()
                .username("a")
                .password("a")
                .email("a@a")
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build();
        userRepository.save(user3);
    }
}