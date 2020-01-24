package com.codecool.servicecheckout.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    String email;
    Long id;
    String password;
    String username;
}
