package com.Test.service;

import com.Test.entity.User;
import com.Test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findEmail(String email)
    {
        User user = userRepository.findByEmail(email).get();
        return user;
    }

    public void verifyUser(User user) {
       user.setEmailVerified(true);
       userRepository.save(user);
    }

    public void setPassword(User user, String newPass) {
        user.setPassword(newPass);
        userRepository.save(user);
    }
}
