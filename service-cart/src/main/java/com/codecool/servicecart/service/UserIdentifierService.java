package com.codecool.servicecart.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserIdentifierService {

    public Long getUserId() {
        // TODO: call service-user with token in request to get user id
        return 1L;
    }
}
