package com.example.currencyConverter.services;

import com.example.currencyConverter.entities.User;

public interface UserService {

    boolean checkUserByUsernameAndPassword(String username, String password);

    boolean checkUserByUsername(String username);

    User findByUsername(String username);

    void save(User user);

}
