package com.Test.controller;

import com.Test.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private EmailVerificationService emailVerificationService;
    @PostMapping("/login-otp/{email}")
    public Map<String,String> sendLogInOtp(@PathVariable String email)
    {
       Map<String,String> response=emailVerificationService.sendOtpLogin(email);
       return response;
    }

    @PostMapping("/verify-otp/{email}/{otp}")
    public Map<String,String> verifyOtpForLogin(@PathVariable String email,@PathVariable String otp)
    {
        Map<String,String> response=emailVerificationService.OtpVerifyLogin(email,otp);
        return response;
    }
}
