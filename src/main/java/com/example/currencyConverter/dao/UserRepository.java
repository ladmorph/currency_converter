package com.example.currencyConverter.dao;

import com.example.currencyConverter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndAndPassword(String username, String password);
}
