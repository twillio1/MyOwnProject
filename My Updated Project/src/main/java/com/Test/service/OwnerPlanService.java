package com.Test.service;

import com.Test.entity.OwnerPlan;
import com.Test.entity.User;
import com.Test.repository.OwnerRepository;
import com.Test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OwnerPlanService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;
    public Map<String, String> createOwnerPlan(long userId, int noOfDays) {

        User user = userRepository.findById(userId).get();

        Map<String,String> map=new HashMap<>();

        if(user!=null) {
            OwnerPlan ownerPlan = new OwnerPlan();

            ownerPlan.setUserId(userId);
            ownerPlan.setNoOfDays(noOfDays);
            ownerPlan.setSubscriptionStatus(true);
            ownerPlan.setSubscriptionStartDate(LocalDateTime.now());
            ownerPlan.setSubscriptionEndDate(LocalDateTime.now().plusDays(noOfDays));

            OwnerPlan save = ownerRepository.save(ownerPlan);

            map.put("Status","Success");
            map.put("Message","Owner Subscribed Successfully");

            return map;

        }else {
            map.put("Status","Failed");
            map.put("Message","Owner Subscription failed");

            return map;
        }

    }
}

