package com.Test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import static com.Test.service.EmailVerificationService.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;



    private String generateOtp() {
        // Define OTP character set
        String numbers = "0123456789";

        // Define length of OTP
        int otpLength = 6; // You can change the length as needed

        // Using StringBuilder to efficiently append characters
        StringBuilder sb = new StringBuilder(otpLength);

        // Initialize random number generator
        Random random = new Random();

        // Generate OTP of specified length
        for (int i = 0; i < otpLength; i++) {
            // Generate random index within the character set length
            int index = random.nextInt(numbers.length());

            // Append character at the generated index to StringBuilder
            sb.append(numbers.charAt(index));
        }

        // Convert StringBuilder to String and return OTP
        return sb.toString();
    }

    public Map<String,String> sendOtp(String email)
    {
        String otp=generateOtp();

        emailOtpMapping.put(email,otp);
        emailOtpExpiry.put(email, System.currentTimeMillis() + 5 * 60 * 1000); // 5 minutes expiration

        sendMail(email,"Otp generation for register the user","Otp is"+otp);

        Map<String,String> response=new HashMap<>();

        response.put("Status","Success");
        response.put("Message","Otp gneration and User Registration successful");

        return response;
    }

    private void sendMail(String email, String s, String s1) {

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(s);
        simpleMailMessage.setText(s1);

        javaMailSender.send(simpleMailMessage);
    }


}
