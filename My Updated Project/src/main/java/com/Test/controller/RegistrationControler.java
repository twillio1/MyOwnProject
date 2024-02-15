package com.Test.controller;

import com.Test.entity.User;
import com.Test.service.EmailService;
import com.Test.service.EmailVerificationService;
import com.Test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reg")
public class RegistrationControler {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/saveuser")
    public Map<String,String> createUser(@RequestBody User user)
    {
       userService.saveUser(user);
        Map<String, String> response = emailService.sendOtp(user.getEmail());
        return response;
    }

    @PostMapping("/verify-otp/{email}/{otp}")
    public Map<String,String> verifyOtp(@PathVariable String email,@PathVariable String otp)
    {
       Map<String,String> response= emailVerificationService.verifyOtp(email,otp);
       return response;
    }
}
