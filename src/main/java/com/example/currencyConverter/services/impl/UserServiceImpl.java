package com.example.currencyConverter.services.impl;


import com.example.currencyConverter.dao.UserRepository;
import com.example.currencyConverter.entities.User;
import com.example.currencyConverter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkUserByUsernameAndPassword(String username, String password) {
        User user  = userRepository.findByLoginAndAndPassword(username, password);
        if (user == null)
            return false;
        else
            return true;
    }
}
