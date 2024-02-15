package com.Test.controller;

import com.Test.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/forgotpassword")
public class ForgotPassWordController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/send-otp/{email}")
    public Map<String,String> sendOtpForForgot(@PathVariable String email)
    {
       Map<String,String> map= emailVerificationService.ssendOtpForForgot(email);
       return map;
    }

    @PostMapping("/reset-pass/{email}/{otp}/{newPass}")
    public Map<String,String> resetPasswordUsingOtp(@PathVariable String email,@PathVariable String otp,@PathVariable String newPass)
    {
        Map<String,String> map =emailVerificationService.resetPass(email,otp,newPass);
        return map;
    }
}
