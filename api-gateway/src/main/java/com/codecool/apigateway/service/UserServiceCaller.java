package com.codecool.apigateway.service;

import com.codecool.apigateway.model.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserServiceCaller {

    @Autowired
    RestTemplate restTemplate;

    @Value("${http://user-service/user}")
    private String url;

    public UserData getUserByName(String username) {
        UserData body = restTemplate.getForEntity(url + "/byname/" + username, UserData.class).getBody();
        log.info("=========EMAIL=============");
        log.info(body.getEmail());
        return body;
    }

   /* public boolean userIsInDataBase(String username, String password){
        UserData userData = restTemplate.getForEntity(url + "/byname/" + username, UserData.class).getBody();
        log.info("==========================USER=====================");
        log.info(userData.getEmail());

    }*/

}
