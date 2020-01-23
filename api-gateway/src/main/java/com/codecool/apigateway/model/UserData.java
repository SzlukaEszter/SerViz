package com.codecool.apigateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserData {

    private Long id;

    private String username;

    private String password;

    private String email;

    private List<String> roles;

}
