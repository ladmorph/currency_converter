package com.example.currencyConverter.services;

import com.example.currencyConverter.entities.User;

public interface UserService {

    boolean checkUserByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    boolean checkUserByUsername(String username);

}
