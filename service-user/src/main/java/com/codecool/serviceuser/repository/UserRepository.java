package com.codecool.serviceuser.repository;

import com.codecool.serviceuser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
