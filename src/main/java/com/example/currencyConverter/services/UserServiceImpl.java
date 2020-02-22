package com.example.currencyConverter.services;


import com.example.currencyConverter.dao.UserRepository;
import com.example.currencyConverter.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null)
            return false;
        else
            return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean checkUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            return false;
        else
            return true;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
