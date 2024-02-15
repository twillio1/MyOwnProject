package com.Test.controller;

import com.Test.service.OwnerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ownerPlan")
public class OwnerPlanController {

    @Autowired
    private OwnerPlanService ownerPlanService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create-subscription/{userId}/{noOfDays}")
    private Map<String,String> createOwnerSubscription(@PathVariable long userId,@PathVariable int noOfDays)
    {
       Map<String,String> map= ownerPlanService.createOwnerPlan(userId,noOfDays);
       return map;
    }
}
