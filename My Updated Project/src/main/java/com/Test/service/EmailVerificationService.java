package com.Test.service;

import com.Test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {

    public final static Map<String,String> emailOtpMapping=new HashMap<>();

    public final static Map<String, Long> emailOtpExpiry=new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private String createSession(String email) {
        // Dummy implementation to generate session ID
        return "Session_" + email.hashCode();
    }


    public Map<String, String> verifyOtp(String email, String otp) {
        String savedOtp = emailOtpMapping.get(email);

        boolean value = emailOtpExpiry.containsKey(email);

        Map<String,String> response=new HashMap<>();

        if(savedOtp!=null && savedOtp.equals(otp) && value==true)
        {
            emailOtpMapping.remove(email);
            User user = userService.findEmail(email);
            if(user!=null)
            {
              userService.verifyUser(user);
                response.put("Status","Success");
                response.put("Message","User Verified");
                return response;
            }else {
                response.put("Status","Failed");
                response.put("Message","User verification failed");
                return response;
            }

        }else{
          response.put("Status","Failed");
          response.put("Message","Otp verification failed");
          return response;
        }
    }

    public Map<String, String> sendOtpLogin(String email) {

        emailService.sendOtp(email);

        String savedOtp = emailOtpMapping.get(email);

        Map<String,String> response=new HashMap<>();

        if(savedOtp!=null)
        {
            response.put("Status","Success");
            response.put("Message","Otp Generted");
            return response;
        }else {
            response.put("Status","Failed");
            response.put("Message","Otp Generation failed");
            return response;
        }
    }

    public Map<String, String> OtpVerifyLogin(String email, String otp) {

        String savedOtp = emailOtpMapping.get(email);
        boolean value = emailOtpExpiry.containsKey(email);
        Long expiryTime = emailOtpExpiry.get(email);

        Map<String,String> response=new HashMap<>();

        if(savedOtp!=null && savedOtp.equals(otp) && value==true && expiryTime != null && System.currentTimeMillis() < expiryTime)
        {

            emailOtpMapping.remove(email);
            emailOtpExpiry.remove(email);
            User user = userService.findEmail(email);
            if(user!=null)
            {
                userService.verifyUser(user);
                // Create session for user (dummy implementation)
                String sessionId = createSession(email);

                response.put("Status", "Success");
                response.put("Message", "Login Successful");
                response.put("SessionId", sessionId);
                return response;
            }else {
                response.put("Status","Failed");
                response.put("Message","User verification failed");
                return response;
            }

        }else{
            response.put("Status","Failed");
            response.put("Message","Otp verification failed");
            return response;
        }
    }

    public Map<String, String> ssendOtpForForgot(String email) {

        User user = userService.findEmail(email);

        Map<String,String> response=new HashMap<>();
        if (user!=null)
        {
            emailService.sendOtp(email);

            String otp = emailOtpMapping.get(email);

            response.put("Status","Success");
            response.put("Message","Otp generated");

            return response;

        }else {
            response.put("Status","Failed");
            response.put("Message","Otp is not generated");

            return response;
        }
    }

    public Map<String, String> resetPass(String email, String otp, String newPass) {

        User user = userService.findEmail(email);

        Map<String,String> response=new HashMap<>();
        if(user!=null)
        {
            String savedOtp = emailOtpMapping.get(email);
            Long expiry = emailOtpExpiry.get(email);
            boolean value = emailOtpExpiry.containsKey(email);

            if(savedOtp!=null && savedOtp.equals(otp) && value==true && expiry>System.currentTimeMillis())
            {
                userService.setPassword(user,newPass);
                response.put("Status","Success");
                response.put("message","Reset Password Success");
                return response;
            }else{
                response.put("Status","Failed");
                response.put("message","Reset Password False");
                return response;
            }

        }else {
            response.put("Status","Failed");
            response.put("message","Reset Password failed");
            return response;
        }
    }
}
