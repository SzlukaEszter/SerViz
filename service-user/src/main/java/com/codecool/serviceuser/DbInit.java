package com.codecool.serviceuser;

import com.codecool.serviceuser.entity.User;
import com.codecool.serviceuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void postConstruct() {
        final User user1 = User.builder()
                .name("Water Sommelier of the Year")
                .email("boss@watermail.com")
                .build();
        userRepository.save(user1);

        final User user2 = User.builder()
                .name("Kovács Béla")
                .email("bela@watermail.com")
                .build();
        userRepository.save(user2);
    }
}